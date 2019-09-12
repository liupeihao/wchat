package com.liupeihao.wchat.plugin.aop;

import com.alibaba.fastjson.JSON;
import com.liupeihao.wchat.plugin.utils.request.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Aspect
@Component
public class LogAopAction {
    @Pointcut("execution(* com.liupeihao.wchat.controller.*.*(..))")
    private void controllerAspect() {
    }

    /**
     * 执行前打印请求参数
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint point) {

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isEmpty(sra)) {
            return;
        }
        HttpServletRequest httpServletRequest = sra.getRequest();
        Map<String,String> map=new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            map.put(name,httpServletRequest.getParameter(name));
        }
        log.info("请求IP: {}", RequestUtil.getRemoteHost(httpServletRequest));
        log.info("请求路径: {}", httpServletRequest.getRequestURI());
        log.info("请求参数: {}", JSON.toJSONString(map));
    }
}
