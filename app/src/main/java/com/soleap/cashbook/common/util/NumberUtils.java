package com.soleap.cashbook.common.util;

import java.text.DecimalFormat;

public class NumberUtils {

    public static String formatDouble(String format, double value) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(value);
    }
}
