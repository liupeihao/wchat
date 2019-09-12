package com.liupeihao.wchat.dto.accesstoken;

import lombok.Data;

/**
 * @ProjectName: giuhub
 * @Package: com.liupeihao.wchat.dto.accesstoken
 * @ClassName: AccessTokenDto
 * @Description:
 * @Author: LPH
 * @CreateDate: 2019/9/9 15:22
 * @UpdateUser:
 * @UpdateDate: 2019/9/9 15:22
 * @UpdateRemark:
 * @Version: 1.0
 */
@Data
public class AccessTokenDto {
    private String  access_token;

    private int  expires_in;

    private String errcode;

    private String errmsg;
}
