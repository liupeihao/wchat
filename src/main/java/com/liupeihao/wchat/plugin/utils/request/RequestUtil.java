package com.liupeihao.wchat.plugin.utils.request;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by @author lph on 2019-05-28.
 */
public class RequestUtil {

    public static final String UNKNOWN = "unknown";

    /**
     * 查找当前请求参数的ip地址
     * @param request request请求对象
     * @return 当前请求的ip
     */
    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (isNotRealIP(ip)) {
            if (isNotRealIP(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (isNotRealIP(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (isNotRealIP(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (isNotRealIP(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (isNotRealIP(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            ip = Arrays.stream(ips)
                    .filter(strIp -> !(UNKNOWN.equalsIgnoreCase(strIp)))
                    .findFirst()
                    .orElse(ip);
        }
        return ip;
    }

    private static boolean isNotRealIP(String ip) {
        return ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip);
    }
}
