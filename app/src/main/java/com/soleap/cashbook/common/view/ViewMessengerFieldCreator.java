package com.soleap.cashbook.common.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;

public class ViewMessengerFieldCreator extends ViewCommunicationFieldCreator {

    public ViewMessengerFieldCreator(ViewDataActivity context, ViewData fieldData) {
        super(context, fieldData);
    }

    @Override
    protected void onUserClicked(String clickValue) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.me/" + clickValue));
        activity.startActivity(i);
    }

    @Override
    protected int getIcon() {
        return R.drawable.ic_messenger;
    }

}
