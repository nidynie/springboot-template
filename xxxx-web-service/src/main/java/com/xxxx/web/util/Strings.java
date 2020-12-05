package com.xxxx.web.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class Strings {

    public static boolean existNotBlank(final String... params) {

        if (ArrayUtils.isEmpty(params)) {
            return false;
        }

        for (String param : params) {
            if (StringUtils.isNotBlank(param)) {
                return true;
            }
        }
        return false;
    }


}
