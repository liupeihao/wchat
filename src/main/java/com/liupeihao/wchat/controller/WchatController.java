package com.liupeihao.wchat.controller;

import com.alibaba.fastjson.JSON;
import com.liupeihao.wchat.dto.qyk.QykResultDto;
import com.liupeihao.wchat.dto.wchatmessage.WchatMessageDto;
import com.liupeihao.wchat.dto.wchatmessage.WchatVerifyDto;
import com.liupeihao.wchat.plugin.base.constants.Constants;
import com.liupeihao.wchat.plugin.base.controller.BaseController;
import com.liupeihao.wchat.plugin.base.result.ReturnCodeType;
import com.liupeihao.wchat.plugin.utils.encrypt.SHA1Utils;
import com.liupeihao.wchat.plugin.utils.http.HttpClientUtils;
import com.liupeihao.wchat.plugin.utils.xml.XmlParseUtils;
import com.liupeihao.wchat.service.IWchatMessageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: giuhub
 * @Package: com.liupeihao.wchat.controller
 * @ClassName: WchatController
 * @Description:
 * @Author: LPH
 * @CreateDate: 2019/9/6 16:24
 * @UpdateUser:
 * @UpdateDate: 2019/9/6 16:24
 * @UpdateRemark:
 * @Version: 1.0
 */

@Slf4j
@RestController
@Api(tags = "WchatAPI")
@RequestMapping(value = "/wchat", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WchatController extends BaseController {

    @Value("${token}")
    private String token;




    @Autowired
    private IWchatMessageService wchatMessageService;
    /**
     *
     * @Author LPH
     * @Date 2019/9/6 19:12
     * @Description
     *  微信 公众号服务回调验证。
     * @return 微信服务器发送的随机字符串
     */
    @RequestMapping(value = "/notice",method = RequestMethod.GET)
    @ResponseBody
    public String validateCard(HttpServletRequest request, WchatVerifyDto dto) {
        try {
            StringBuilder sb=new StringBuilder();
            sb.append(dto.getTimestamp()).append(dto.getNonce()).append(token);
            String result = SHA1Utils.sha1(sb.toString());
            if(result.equalsIgnoreCase(dto.getSignature())){
                return dto.getEchostr();
            }
            return ERROR(ReturnCodeType.SIGNATURE_ERROR.getCode());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ERROR(ReturnCodeType.BASE_ERROR.getCode());
        }
    }

    @RequestMapping(value = "/notice",method = RequestMethod.POST,produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String receiveMessage(@RequestBody WchatMessageDto dto){
        try {
            String r_content=wchatMessageService.receiveMessage(dto);
            return r_content;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Constants.SUCCESS;
        }
    }

}
