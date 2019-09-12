package com.liupeihao.wchat.plugin.base.result;

import java.util.Arrays;

/**
 * @author LPH
 * @Title: StatusType
 * @ProjectName maidong_platform
 * @Description: TODO
 * @date 2018/11/9 000911:11
 */

public enum StatusType {
    /**
     * 成功
     */
    SUCCESS(1, "成功"),
    /**
     * 失败
     */
    ERROR(0, "失败");
    /**
     * code
     */
    private int code;

    /**
     * name
     */
    private String name;

    StatusType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(int code) {
        return Arrays.stream(StatusType.values())
                .filter(enuma -> enuma.getCode() == code)
                .findFirst()
                .map(StatusType::getName)
                .orElse("未知枚举项");
    }


    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


}
