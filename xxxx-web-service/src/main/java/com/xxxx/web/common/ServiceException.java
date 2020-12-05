package com.xxxx.web.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义业务异常
 */
public class ServiceException extends RuntimeException {

    private String code;
    private String msg;

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public ServiceException(CommonMessage message) {
        super(message.getMessage());
        this.code = message.getCode();
        this.msg = message.getMessage();
    }

    /**
     * 含有枚举类的构造方法，主要用于其他服务响应提示信息
     *
     * @author huangxl
     * @date 2018/9/27 11:13
     **/
    public ServiceException(Enum emusMessage) {
        super("enum message");
        String code = "";
        String msg = "";
        Class<?> enumClass = emusMessage.getClass();
        try {
            Method getCode = enumClass.getDeclaredMethod("getCode");
            Method getMsg = enumClass.getDeclaredMethod("getMsg");
            code = getCode.invoke(emusMessage).toString();
            msg = getMsg.invoke(emusMessage).toString();
        } catch (NoSuchMethodException nme) {
            System.out.println("=====自定义响应信息的枚举类请提供 getCode()和getMsg() 两个方法====");
            System.out.println(nme.toString());
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e);//仅控制台打印
        }

        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
