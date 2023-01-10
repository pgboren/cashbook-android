package com.soleap.cashbook.common.util;

import static android.Manifest.permission.CALL_PHONE;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ActivityUtils {

    private static ActivityUtils instance;

    private Activity context;

    public ActivityUtils(Activity context) {
        this.context = context;
    }

    public static ActivityUtils getInstance(Activity context) {
        if (instance == null) {
            instance = new ActivityUtils(context);
        }
        return instance;
    }

    public String getPackageName() {
        return this.context.getPackageName();
    }

    public Resources getResources() {
        return context.getResources();
    }

    public  int getResourceId(String name, String defType, String defPackage) {
        int resId = getResources().getIdentifier(name, defType, getPackageName());
        return resId;
    }

    public String getStringResource(String aString) {
        String packageName = getPackageName();
        int resId = getResourceId(aString, "string", packageName);
        if (resId == 0) {
            return aString;
        }
        return context.getString(resId);
    }

    public void CallPhone(String phonenumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phonenumber));
        if (ContextCompat.checkSelfPermission(context, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            context.startActivity(intent);
        } else {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{CALL_PHONE}, 1);
        }
    }
}
