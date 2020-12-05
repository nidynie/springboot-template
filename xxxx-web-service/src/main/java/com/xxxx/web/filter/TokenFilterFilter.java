package com.xxxx.web.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * wrapper 拦截器，用于组装body数据到wrapperRequest中,
 * 对象中的requestBody数据就是body的副本数据
 */
@Order(1)
@Configuration
@WebFilter(filterName = "tokenFilter", urlPatterns = "/*")
public class TokenFilterFilter implements Filter {


//    @Autowired
//    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest servletRequest = ((HttpServletRequest) request);


        String token = servletRequest.getHeader("token");
//
//        if (StringUtils.isNotBlank(token)) {
//            UserVo userVo = userService.getUserInfoFromRedisByToken(token);
//            servletRequest.getSession().setAttribute("user", userVo);
//        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
