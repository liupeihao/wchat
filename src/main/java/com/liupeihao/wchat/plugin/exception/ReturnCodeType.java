package com.liupeihao.wchat.plugin.exception;


import java.util.Arrays;

/**
 * @author LPH
 * @Title: ReturnCodeType
 * @ProjectName maidong_platform
 * @date 2018/11/9 000911:12
 */
public enum ReturnCodeType {

    //政务服务  添加服务分类唯一标识
    BASE_SUCCESS("10001", "成功"),
    BASE_ERROR("10002", "系统异常"),
    SIGNATURE_ERROR("WX10003", "验签失败"),

    ;


    private String code;

    /**
     * 显示产品名称
     */
    private String name;

    ReturnCodeType(String code, String name) {
        this.code = code;
        this.name = name;
    }


    public static String getNameByCode(String code) {
        return Arrays.stream(ReturnCodeType.values())
                .filter(codeType -> codeType.getCode().equals(code))
                .findFirst()
                .map(ReturnCodeType::getName)
                .orElse("未知枚举项");
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
