package com.xxxx.web.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TimeUtil {


    public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String DATE_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";


    public static int compareDate(String date1, String date2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long datetime1 = simpleDateFormat.parse(date1).getTime();
            long datetime2 = simpleDateFormat.parse(date2).getTime();
            if (datetime1 - datetime2 > 0) {
                return 1;
            } else if (datetime1 - datetime2 == 0) {
                return 0;
            } else {
                return -1;
            }
        } catch (Exception e) {
            throw new RuntimeException("日期格式错误，正确案例为：2019-01-01");
        }
    }


    /**
     * 获取日期
     *
     * @param time
     * @return
     * @throws Exception
     */
    public static Date getDate(Date time) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(sdf.format(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public static Integer getIntDateValue(Date date) {
        String dateValue = format(date, "yyyyMMdd");
        return Integer.parseInt(dateValue);
    }

    public static String format(Date date, String pattern) {
        if (date == null || StringUtils.isBlank(pattern)) {
            return "";
        }

        try {
            String time = new SimpleDateFormat(pattern).format(date);
            return time;
        } catch (Exception e) {
            log.info("转换时间格式出差,date:{},pattern:{}", date, pattern, e);

        }
        return "";
    }

    public static String formatDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String formatTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:dd");
    }
}
