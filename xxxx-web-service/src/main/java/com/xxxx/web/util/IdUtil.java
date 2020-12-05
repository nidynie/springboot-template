package com.xxxx.web.util;

import java.util.Date;

public class IdUtil {

    private static Sequence sequence = new Sequence();


    public static String getId() {
        return String.valueOf(sequence.nextId());
    }

    public static String getMillsId() {
        return TimeUtil.format(new Date(), "yyyyMMddHHmmssSSS");
    }
}
