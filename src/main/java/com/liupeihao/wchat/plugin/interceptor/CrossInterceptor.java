package com.liupeihao.wchat.plugin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by @author fww on 2019-05-24.
 */
@Slf4j
@Component
public class CrossInterceptor implements HandlerInterceptor {

    private static final String OPTIONS = "OPTIONS";

    @Value("${allowUrls}")
    private String[] allowUrls;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (OPTIONS.equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        String origin=request.getHeader("Origin");
        for (String everyUrl : allowUrls) {
            if (everyUrl.equals(origin)) {
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Methods", "POST");
                response.addHeader("Access-Control-Allow-Credentials", "true");
                response.addHeader("Access-Control-Allow-Headers", "Content-Type,token");
                break;
            }
        }
        return true;
    }
}
