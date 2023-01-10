package com.soleap.cashbook.common.view;

import android.content.Context;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.IntentUtil;

public class ViewPhoneNumberFieldCreator extends ViewCommunicationFieldCreator {

    public ViewPhoneNumberFieldCreator(ViewDataActivity context, ViewData fieldData) {
        super(context, fieldData);
    }

    @Override
    protected void onUserClicked(String clickValue) {
        IntentUtil.CallPhone(clickValue, activity);
    }

    @Override
    protected int getIcon() {
        return R.drawable.ic_call_24;
    }

}
