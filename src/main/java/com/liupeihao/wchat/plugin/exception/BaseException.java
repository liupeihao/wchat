package com.liupeihao.wchat.plugin.exception;

import com.liupeihao.wchat.plugin.base.result.ReturnCodeType;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = -3605030911925262261L;
    private String msg;
    private String code;

    public BaseException(String code) {
        super(code);
        initBaseException(code, ReturnCodeType.getNameByCode(code));
    }

    public BaseException(String code, String message) {
        super(code);
        initBaseException(code,message);
    }

    public BaseException(String code, String message, Throwable cause ) {
        super(code, cause);
        initBaseException(code,message);
    }

    public BaseException(String code, Throwable cause) {
        super(code,cause);
        this.code = code;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        initBaseException(code,message);
    }

    public BaseException(ReturnCodeType returnCodeType){
        super(returnCodeType.getCode());
        initBaseException(returnCodeType.getCode(),returnCodeType.getName());
    }

    public BaseException(ReturnCodeType returnCodeType, Throwable cause){
        super(cause);
        initBaseException(returnCodeType.getCode(),returnCodeType.getName());
    }

    private void initBaseException(String code, String message){
        this.msg = message;
        this.code = code;
    }
}
