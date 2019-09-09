package com.liupeihao.wchat.dto.wchatmessage;

import lombok.Data;
import lombok.ToString;

/**
 * @ProjectName: giuhub
 * @Package: com.liupeihao.wchat.dto
 * @ClassName: WchatVerifyDto
 * @Description:
 * @Author: LPH
 * @CreateDate: 2019/9/6 16:31
 * @UpdateUser:
 * @UpdateDate: 2019/9/6 16:31
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
@ToString
public class WchatVerifyDto {
    private String echostr;
    private String nonce;
    private long timestamp;
    private String signature;


}
