package com.soleap.cashbook.common.util;

import android.content.Context;

public class ResourceUtil {

    public static String getStringResourceByName(Context context, String aString) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        if (resId == 0) {
            return aString;
        }
        return context.getString(resId);
    }




}
