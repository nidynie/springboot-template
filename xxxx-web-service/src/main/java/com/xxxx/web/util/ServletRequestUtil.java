package com.xxxx.web.util;


import com.xxxx.web.common.CommonMessage;
import com.xxxx.web.common.ServiceException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ServletRequestUtil {

//
//    public static UserVo getUserFromRequest() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Object object = request.getSession().getAttribute("user");
//        if (object != null && object instanceof UserVo) {
//            return (UserVo) object;
//        }
//        throw new ServiceException(CommonMessage.SERVER_ERROR);
//    }
//
//
//    public static String getUserIdFromRequest() {
//        UserVo userVo = getUserFromRequest();
//        if (userVo != null) {
//            return userVo.getId();
//        }
//        throw new ServiceException(CommonMessage.SERVER_ERROR);
//    }
//
//
//    public static String tryGetUserId() {
//        try {
//            return getUserIdFromRequest();
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
