package com.xxxx.web.filter;

import com.xxxx.web.wrapper.BodyReaderHttpServletRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * wrapper 拦截器，用于组装body数据到wrapperRequest中,
 * 对象中的requestBody数据就是body的副本数据
 */

@Order(value = Integer.MIN_VALUE)
@Configuration
@WebFilter(filterName = "bodyReaderFilter", urlPatterns = "/*")
public class HttpSignFilter implements Filter {


    private static final String contentType = "application/json;charset=UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String contentType = servletRequest.getHeader(HttpHeaders.CONTENT_TYPE);
        if (StringUtils.isNotBlank(contentType) && contentType.toLowerCase().contains(MediaType.APPLICATION_JSON_VALUE)) {
            BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper(servletRequest);
            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(servletRequest, response);
        }


    }


    @Override
    public void destroy() {

    }
}
