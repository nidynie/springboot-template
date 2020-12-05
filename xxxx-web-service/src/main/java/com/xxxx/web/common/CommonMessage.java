package com.xxxx.web.common;

public enum CommonMessage {
    SUCCESS("0", "请求处理成功"),
    REQUEST_PARAM_INVALID("100100", "无效的请求参数"),
    USER_PASSWORD_INVALID("100101", "无效的用户名或密码"),
    USER_NAME_EXIST("100102", "用户名已经存在"),
    USER_PHONE_EXIST("100102", "手机号码已经存在"),
    PAGE_BEAN_INVALID("100103", "分页关键参数异常"),
    EMAIL_INVALID("100104", "电子邮箱格式正确"),
    PHONE_INVALID("100105", "手机号码格式不正确"),
    IDCARD_INVALID("100106", "身份证号码格式不正确"),
    START_TIME_GT_END_TIME("100107", "开始时间大于结束时间"),
    ID_CARD_EXIST("100108", "身份证号码已经存在，请检查后重试"),
    CAN_BAO_REN_NOT_EXIST("100109", "参保人信息不存在"),
    TARGET_ID_CARD_EXIST("100110", "更新的身份证号码已经存在，请检查后重试"),
    CURRENT_YEAR_MONTH_DATA_EXIST("100111", "在参保时间当年中，此月份的数据已经存在"),
    NOT_LOGIN("100401", "请先登录！"),
    TOKEN_ERROR("100402", "登录信息异常，请重新新登录"),
    ROLE_RESOURCE_INVALID("100403", "您当前无权限执行该操作"),
    RESOURCE_NOT_SET("100405", "没有获取到可用的菜单资源，请检查"),
    ROLE_RESOURCE_NOT_SET("100406", "用户没有设置权限或者权限没有配置对应的资源"),
    ROLE_IN_USED("100407", "当前角色已被其他用户引用"),
    SERVER_ERROR("100500", "服务器开了小差，请稍后重试"),
    BUSINESS_ERROR("-1", "业务异常"),
    ERROR("1", "请求异常");

    private String code;

    private String message;


    CommonMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
