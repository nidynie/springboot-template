package com.xxxx.web.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.MimeHeaders;
import java.lang.reflect.Field;

@Slf4j
public class RequestUtils {


    /**
     * 本方法只能在springboot请求生命周期内使用
     * 需要nginx 配置x-real-ip 代理请求头配置
     *
     * @return
     */
    public static String getRequestIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        String ip = request.getHeader("x-real-ip");
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void addHeaders(HttpServletRequest request, String key, String value) {

        if (StringUtils.isNotBlank(key) || value == null) {
            throw new NullPointerException("key can not blank or value can not be null");
        }
        Class<? extends HttpServletRequest> requestClass = request.getClass();
        try {
            Field request1 = requestClass.getDeclaredField("request");
            request1.setAccessible(true);
            Object o = request1.get(request);
            Field coyoteRequest = o.getClass().getDeclaredField("coyoteRequest");
            coyoteRequest.setAccessible(true);
            Object o1 = coyoteRequest.get(o);
            Field headers = o1.getClass().getDeclaredField("headers");
            headers.setAccessible(true);
            MimeHeaders o2 = (MimeHeaders) headers.get(o1);
            o2.addHeader(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
