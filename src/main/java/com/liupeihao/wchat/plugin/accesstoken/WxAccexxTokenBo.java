package com.liupeihao.wchat.plugin.accesstoken;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: giuhub
 * @Package: com.liupeihao.wchat.plugin.accesstoken
 * @ClassName: WxAccexxTokenBo
 * @Description:
 * @Author: LPH
 * @CreateDate: 2019/9/11 11:54
 * @UpdateUser:
 * @UpdateDate: 2019/9/11 11:54
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "accesstoken")
public class WxAccexxTokenBo {
    private String accessTokenUrl;
    private String appId;
    private String secret;
}
