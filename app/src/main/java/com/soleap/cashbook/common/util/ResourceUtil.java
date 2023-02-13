package com.soleap.cashbook.common.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.soleap.cashbook.R;

public class ResourceUtil {

    public static String getStringResourceByName(Context context, String aString) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        if (resId == 0) {
            return aString;
        }
        return context.getString(resId);
    }

    public static Drawable getDrawableResourceByName(Context context, String aString) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "drawable", packageName);
        return context.getDrawable(resId);
    }






}
