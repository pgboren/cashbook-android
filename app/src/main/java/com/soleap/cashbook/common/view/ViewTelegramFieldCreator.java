package com.soleap.cashbook.common.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.AppUtils;

public class ViewTelegramFieldCreator extends ViewCommunicationFieldCreator {

    public ViewTelegramFieldCreator(ViewDataActivity context, ViewData fieldData) {
        super(context, fieldData);
    }

    @Override
    protected void onUserClicked(String clickValue) {
        final String appName = "org.telegram.messenger";
        final boolean isAppInstalled = AppUtils.isAppAvailable(activity.getApplicationContext(), appName);
        if (isAppInstalled)
        {
            Uri uri = new Uri.Builder().scheme("http").authority("telegram.me").appendEncodedPath(clickValue).build();
            Intent myIntent = new Intent(Intent.ACTION_VIEW, uri).setPackage("org.telegram.messenger");
            activity.startActivity(myIntent);
        }
        else
        {
            Toast.makeText(activity, "Telegram not Installed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int getIcon() {
        return R.drawable.ic_telegram;
    }

}
