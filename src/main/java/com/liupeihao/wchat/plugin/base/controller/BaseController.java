package com.liupeihao.wchat.plugin.base.controller;

import com.alibaba.fastjson.JSON;
import com.liupeihao.wchat.plugin.base.result.DataObject;
import com.liupeihao.wchat.plugin.base.result.ReVo;
import com.liupeihao.wchat.plugin.base.result.StatusType;
import com.liupeihao.wchat.plugin.base.result.ReturnCodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author LPH
 * @Title: BaseController
 * @ProjectName maidong_platform
 * @Description: TODO
 * @date 2018/11/9 000911:06
 */
public class BaseController {

    private static Logger log = LoggerFactory.getLogger(BaseController.class);


    private String toJSONString(ReVo reVo){
        String responseStr = JSON.toJSONString(reVo);
        log.info("返回参数:{}",responseStr);
        log.info(responseStr);
        return responseStr;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    protected String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
       log.info("获取客户端ip:" + ip);
        return ip;
    }




    //失败仅返回消息
    protected String ERROR(String returnCode){
        ReVo reVo=new ReVo(StatusType.ERROR.getCode(),StatusType.ERROR.getName(),returnCode, ReturnCodeType.getNameByCode(returnCode));
        reVo.setData(new DataObject());
        return toJSONString(reVo);
    }

    protected String VALIDATE_ERROR(Map<String, String> validateMap){
        ReVo reVo=new ReVo(StatusType.ERROR.getCode(),StatusType.ERROR.getName(),ReturnCodeType.BASE_ERROR.getCode(),ReturnCodeType.BASE_ERROR.getName());
        reVo.setValidateMsg(validateMap);
        return toJSONString(reVo);
    }


    protected String ERROR(String returnCode,String returnMsg){
        ReVo reVo=new ReVo(StatusType.ERROR.getCode(),StatusType.ERROR.getName(),returnCode,returnMsg);
        reVo.setData(new DataObject());
        return toJSONString(reVo);
    }

    protected String SUCCESS(String returnCode){
        ReVo reVo=new ReVo(StatusType.SUCCESS.getCode(),StatusType.SUCCESS.getName(),returnCode,ReturnCodeType.getNameByCode(returnCode));
        reVo.setData(new DataObject());
        return toJSONString(reVo);
    }

    protected String SUCCESS(String returnCode,Object data){
        ReVo reVo=new ReVo(StatusType.SUCCESS.getCode(),StatusType.SUCCESS.getName(),returnCode, ReturnCodeType.getNameByCode(returnCode),data);
        return toJSONString(reVo);
    }

    protected String SUCCESS(String returnCode,Object rows,int total){
        ReVo reVo=new ReVo(StatusType.SUCCESS.getCode(),StatusType.SUCCESS.getName(),returnCode,ReturnCodeType.getNameByCode(returnCode),rows,total);
        return toJSONString(reVo);
    }
    protected String SUCCESS(Object rows,int total){
        ReVo reVo=new ReVo(StatusType.SUCCESS.getCode(),StatusType.SUCCESS.getName(),ReturnCodeType.BASE_SUCCESS.getCode(),ReturnCodeType.BASE_SUCCESS.getName(),rows,total);
        return toJSONString(reVo);
    }





}
