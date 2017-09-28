package com.chen.common.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author long
 * <p>
 * date 17-9-26
 * <p>
 * desc
 */
public class FormatUtil {

    public static Date formatDate(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return formatter.parse(dateString);
    }
}
