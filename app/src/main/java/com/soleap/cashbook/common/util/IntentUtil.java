package com.soleap.cashbook.common.util;

import static android.Manifest.permission.CALL_PHONE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class IntentUtil {

    public static void CallPhone(String phonenumber,  Context context) {
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
