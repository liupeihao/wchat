package com.liupeihao.wchat.service.impl;

import com.alibaba.fastjson.JSON;
import com.liupeihao.wchat.dto.qyk.QykResultDto;
import com.liupeihao.wchat.dto.userinfo.WchatUserInfo;
import com.liupeihao.wchat.dto.wchatmessage.WchatMessageDto;
import com.liupeihao.wchat.mapper.WchatMessageMapper;
import com.liupeihao.wchat.plugin.base.constants.Constants;
import com.liupeihao.wchat.plugin.base.service.impl.BaseServiceImpl;
import com.liupeihao.wchat.plugin.utils.date.DateUtils;
import com.liupeihao.wchat.plugin.utils.http.HttpClientUtils;
import com.liupeihao.wchat.plugin.utils.idworker.IdWorkerUtilss;
import com.liupeihao.wchat.plugin.utils.xml.XmlParseUtils;
import com.liupeihao.wchat.service.IWchatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.liupeihao.wchat.entity.WchatMessage;

import javax.xml.bind.JAXBException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LPH
 * @since 2019-09-09
 */
@Service
@Slf4j
public class WchatMessageServiceImpl extends BaseServiceImpl<WchatMessage> implements IWchatMessageService<WchatMessage> {

    //青云客请求路径
    @Value("${qingyunkeURL}")
    private String qingyunkeURL;
    //青云客appId
    @Value("${qingyunkeAppId}")
    private String qingyunkeAppId;
    //青云客key
    @Value("${qingyunkeKey}")
    private String qingyunkeKey;
    //请求微信用户信息url
    @Value("${userInfoUrl}")
    private String userInfoUrl;


    @Autowired
    private WchatMessageMapper wchatMessageMapper;


    @Override
    public String receiveMessage(WchatMessageDto dto) throws JAXBException {
        //暂时只处理文本类型
        if (Constants.TEXT.equals(dto.getMsgType())) {
            StringBuilder sb = new StringBuilder();
            String url = sb.append(qingyunkeURL)
                    .append("?key=")
                    .append(qingyunkeKey)
                    .append("&appid=")
                    .append(qingyunkeAppId)
                    .append("&msg=")
                    .append(dto.getContent()).toString();
            log.info("青云客URL:{}", url);
            String qyk_result = HttpClientUtils.get(url);
            log.info("青云客返回参数:{}", qyk_result);
            QykResultDto qykResultDto = JSON.parseObject(qyk_result, QykResultDto.class);
            WchatMessageDto r_content = WchatMessageDto.builder().ToUserName(dto.getFromUserName())
                    .FromUserName(dto.getToUserName())
                    .CreateTime(System.currentTimeMillis())
                    .MsgType(Constants.TEXT)
                    .build();
            if (Constants.SUCCESS_STATUS == qykResultDto.getResult()) {
                r_content.setContent(qykResultDto.getContent());
            } else {
                r_content.setContent(new StringBuilder(dto.getContent()).reverse().toString());
            }
            //获取用户信息
            sb.setLength(0);
            sb.append(userInfoUrl)
                    .append("?")
                    .append("access_token=")
                    .append(Constants.ACCESS_TOKEN)
                    .append("&openid=")
                    .append(dto.getFromUserName())
                    .append("&lang=zh_CN");
            String wx_userInfo = HttpClientUtils.get(sb.toString());
            log.info("获取用户信息返回参数:{}", wx_userInfo);
            WchatUserInfo wchatUserInfo = JSON.parseObject(wx_userInfo, WchatUserInfo.class);

            WchatMessage wchatMessage = WchatMessage.builder().id(IdWorkerUtilss.getIdStr())
                    .createTime(DateUtils.getCurrentTimeStr())
                    .fromUserNamr(wchatUserInfo.getNickname())
                    .msgType(Constants.TEXT)
                    .fromUserOpenId(dto.getFromUserName()).build();
            wchatMessageMapper.insert(wchatMessage);
            String result_content = XmlParseUtils.beanToXml(r_content);
            log.info("响应用户消息:{}", result_content);
            return result_content;
        }
        //非文本直接返回
        return Constants.SUCCESS;
    }
}
