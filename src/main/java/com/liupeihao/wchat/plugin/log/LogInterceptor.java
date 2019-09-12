package com.liupeihao.wchat.plugin.log;

import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


public class LogInterceptor implements HandlerInterceptor {

    private static final String THREAD_ID = "threadID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put(THREAD_ID, UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(THREAD_ID);
    }
}
