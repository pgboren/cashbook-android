package com.soleap.cashbook.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

    public static String format(Date date, String format) {
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        return df.format(format, date).toString();
    }

    public static String format(long data, String format) {
        Date date = new Date(data);
        return  format(date, format);
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
