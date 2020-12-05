package com.xxxx.web.filter;

import com.alibaba.fastjson.JSON;
import com.xxxx.web.common.CommonMessage;
import com.xxxx.web.common.Result;
import com.xxxx.web.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


/**
 * 用户身份校验拦截器
 */
@Slf4j
@Configuration
public class AuthInterceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHandlerInterceptor())
                //拦截所有请求
                .addPathPatterns("/**")
                //放行请求
                .excludePathPatterns("/")
                .excludePathPatterns("/api/weiXinUser/**")
                .excludePathPatterns("/api/user/refreshToken")
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/user/logout")
                .excludePathPatterns("/swagger-ui.html/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/webjars/**");
    }


    @Bean
    public HandlerInterceptor getHandlerInterceptor() {
        return new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                log.info("进入拦截器，当前请求:{}>>:{}", request.getMethod(), request.getServletPath());
                log.info("request ip:{}", RequestUtils.getRequestIp());
                HttpSession session = request.getSession();
//                UserVo userVo = (UserVo) session.getAttribute("user");
//
//
//                if (!"OPTIONS".equals(request.getMethod()) && userVo == null && "wx".equals(request.getHeader("client"))) {
//                    errorResponse(response, CommonMessage.NOT_LOGIN);
//                    return false;
//                }
//
//                if (userVo == null) {
//                    errorResponse(response, CommonMessage.NOT_LOGIN);
//                    return false;
//                }

//                if (handler instanceof HandlerMethod) {
//                    HandlerMethod handlerMethod = (HandlerMethod) handler;
//                    // 获取方法上的注解
//                    RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
//                    // 如果注解为null, 说明不需要拦截, 直接放过
//                    if (requiredPermission == null || requiredPermission.value().length == 0) {
//                        return true;
//                    } else {
//
//
//                        if (!Arrays.asList(requiredPermission.value()).contains(userVo.getRole().name())) {
//                            errorResponse(response, CommonMessage.ROLE_RESOURCE_INVALID);
//                            return false;
//                        }
//                    }
//
//
//                }
//                session.setAttribute("user", userVo);
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//                Object userObject = request.getSession().getAttribute("user");
//                if (userObject != null && userObject instanceof UserVo) {
//                    userService.updateTokenUserInfo((UserVo) userObject, request.getHeader("TOKEN"));
//                }
            }
        };
    }


    private void errorResponse(HttpServletResponse response, CommonMessage commonMessage) throws Exception {
        log.info("请求被拦截，原因:{}", commonMessage.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        /* 允许跨域的主机地址 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        response.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        response.setHeader("Access-Control-Allow-Credentials", "true");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(Result.getResult(commonMessage)));
        printWriter.flush();
        printWriter.close();
    }
}

