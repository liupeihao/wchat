package com.liupeihao.wchat.plugin.accesstoken;

import com.alibaba.fastjson.JSON;
import com.liupeihao.wchat.dto.accesstoken.AccessTokenDto;
import com.liupeihao.wchat.plugin.base.constants.Constants;
import com.liupeihao.wchat.plugin.utils.date.DateUtils;
import com.liupeihao.wchat.plugin.utils.http.HttpClientUtils;
import com.liupeihao.wchat.plugin.utils.string.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @ProjectName: giuhub
 * @Package: com.liupeihao.wchat.plugin.accesstoken
 * @ClassName: AccessTokenManager
 * @Description:
 * @Author: LPH
 * @CreateDate: 2019/9/9 14:21
 * @UpdateUser:
 * @UpdateDate: 2019/9/9 14:21
 * @UpdateRemark:
 * @Version: 1.0
 */
@Slf4j
@Configuration
public class AccessTokenManager implements ApplicationRunner {

    @Autowired
    private WxAccexxTokenBo wxAccexxTokenBo;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        while(true){
            StringBuilder sb=new StringBuilder();
            sb.append(wxAccexxTokenBo.getAccessTokenUrl())
                    .append("&appid=")
                    .append(wxAccexxTokenBo.getAppId())
                    .append("&secret=")
                    .append(wxAccexxTokenBo.getSecret());
            String accessTokenJson = HttpClientUtils.get(sb.toString());
            AccessTokenDto dto= JSON.parseObject(accessTokenJson,AccessTokenDto.class);
            if(StringUtils.isNotBlank(dto.getErrcode())){
                log.info("获取access_token失败，错误码:{},错误消息为:{}",dto.getErrcode(),dto.getErrmsg());
            }
            log.info("获取accessToken成功:{},当前时间为:{}",dto.getAccess_token(), DateUtils.getCurrentTimeStr());
            Constants.ACCESS_TOKEN=dto.getAccess_token();
            Thread.sleep(59*60*2*1000);
        }
    }
}
