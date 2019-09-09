package com.liupeihao.wchat.plugin.base;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Map;

/**
 * @author LPH
 * @Title: Revo
 * @ProjectName maidong_platform
 * @Description: TODO
 * @date 2018/11/9 000911:14
 */

public class ReVo implements Serializable {

    private static final long serialVersionUID = 1L;
    @JSONField(ordinal = 1)
    //成功失败状态
    private int status;

    //业务返回码
    @JSONField(ordinal = 2)
    private String returnCode;

    //业务返回信息
    @JSONField(ordinal = 3)
    private String returnMsg;

    @JSONField(ordinal = 4)
    private Object data;

    private int total;

    private Map<String,String> validateMsg;

    public ReVo(int status, String msg, String returnCode, String returnMsg) {
        this.status = status;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public ReVo(int status, String msg, String returnCode, String returnMsg, Object r_data) {
        this.status = status;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.data=r_data;
    }

    public ReVo(int status, String msg, String returnCode, String returnMsg,Object rows,int total) {
        this.status = status;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.data=rows;
        this.total=total;
    }

    public ReVo(int status, String msg,Object rows,int total) {
        this.status = status;
        this.data=rows;
        this.total=total;
    }

    public Map<String, String> getValidateMsg() {
        return validateMsg;
    }

    public void setValidateMsg(Map<String, String> validateMsg) {
        this.validateMsg = validateMsg;
    }

    public ReVo(int status, String msg, ListObject r_list) {
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
