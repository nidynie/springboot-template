package com.xxxx.web.common;

/**
 * 正则表达式常量定义
 * @author huangxl
 * @version V1.0
 * @package com.shijiyunhe.saas.com.shijiyunhe.evaluate.common.constants
 * @date 2018/9/27 20:24
 **/
public interface RegexConstants {

    /** 11位手机号码 */
    String PHONE_PATTERN = "^1[2-9][0-9]{9}$";
    /** 邮箱*/
    String EMAIL_PATTERN = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";

    /** 大小写字母和数字组合 */
    String LETTER_AND_NUMBER_PATTERN = "^[A-Za-z0-9]+$";
    /** 正整数 */
    String POSITIVE_PATTERN = "^[1-9][0-9]*$";
    /** 0或正整数 */
    String ZERO_OR_POSITIVE_PATTERN = "^(0|[1-9][0-9]*)$";

    /** 日期模式，仅匹配年月日，格式：yyyy-MM-dd */
    String DATE_PATTERN = "^((19|2[0-9])\\d{2})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[12][0-9])))$";

    /** 18位身份证号码 */
    String IDCARD_PATTERN = "^(\\d{6})(19|20)(\\d{2})(0[1-9]|1[012])([0123]\\d)(\\d{3})(\\d|X|x)$";
}
