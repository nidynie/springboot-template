package com.xxxx.web.util;

import org.apache.commons.lang3.StringUtils;

public class FormatUtil {

    public static String formatPhoneNumber(String phone) {
        if (StringUtils.isBlank(phone)) {
            return phone;
        }
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

    public static String formatQQ(String qq) {
        if (StringUtils.isBlank(qq)) {
            return qq;
        }

        return qq.substring(0, 2) + "****" + qq.substring(qq.length() - 2);
    }
}
