package com.xxxx.web.config;

import com.xxxx.web.common.CommonMessage;
import com.xxxx.web.common.Result;
import com.xxxx.web.common.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * controller 公共异常统一处理类
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 自定义业务异常处理，响应页面信息
     *
     * @author huangxl
     * @date 2018/9/26 14:58
     **/
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public Result serviceExceptionHandler(ServiceException ex) {
        //自定义异常只记录提示信息
        log.error("serviceExceptionHandler code:{}, msg:{}", ex.getCode(), ex.getMsg());
        return new Result(ex.getCode(), ex.getMsg(), null);
    }


    /**
     * 用来拦截特殊的参数异常
     *
     * @author huangxl
     * @date 2018/9/26 14:57
     **/
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public Result illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        log.error("exceptionHandler: {}", ex);
        ex.printStackTrace();
        return new Result(CommonMessage.REQUEST_PARAM_INVALID.getCode(), ex.getMessage(), null);
    }


    /**
     * 系统全局异常处理
     *
     * @author huangxl
     * @date 2018/9/26 14:57
     **/
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException ex) {
        log.error("exceptionHandler: {}", ex);
        ex.printStackTrace();
        return new Result(CommonMessage.SERVER_ERROR.getCode(), CommonMessage.SERVER_ERROR.getMessage(), null);
    }


    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleApiConstraintViolationException(ConstraintViolationException ex) {
        String message = "";
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message += violation.getMessage() + ", ";
        }

        return new Result(CommonMessage.REQUEST_PARAM_INVALID.getCode(), message, null);
    }

    /**
     * 处理参数校验异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        return new Result(CommonMessage.REQUEST_PARAM_INVALID.getCode(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), null);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result bindException(Exception ex) {
        log.error("请求出错，错误信息：{}", ex.getMessage(), ex);
        return new Result(CommonMessage.SERVER_ERROR.getCode(), CommonMessage.BUSINESS_ERROR.getMessage(), null);
    }


}
