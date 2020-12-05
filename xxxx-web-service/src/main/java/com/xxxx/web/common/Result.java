package com.xxxx.web.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;


/**
 * 请求响应数据封装类
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    /**
     * 错误码 0 为正常 其他异常
     */
    private String errorCode;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 响应结果数据
     */
    private T data;


    /**
     * 返回无数据的成功结果
     *
     * @return
     */
    public static Result success() {
        return getResult(CommonMessage.SUCCESS, new HashMap<>());
    }

    /**
     * 返回无数据的成功结果
     *
     * @return
     */
    public static <T> Result<T> success(T data) {
        return getResult(CommonMessage.SUCCESS, data);
    }

    /**
     * 返回无数据的结果
     *
     * @param commonMessage
     * @return
     */
    public static Result getResult(CommonMessage commonMessage) {
        return getResult(commonMessage, new HashMap<>());
    }

    public static Result getResult(String errorCode, String message) {

        return new Result(errorCode, message, new HashMap<>());
    }

    /**
     * 返回有数据的结果
     *
     * @param commonMessage
     * @param data
     * @return
     */
    public static <T> Result<T> getResult(CommonMessage commonMessage, T data) {
        return new Result<>(commonMessage.getCode(), commonMessage.getMessage(), data);
    }


}
