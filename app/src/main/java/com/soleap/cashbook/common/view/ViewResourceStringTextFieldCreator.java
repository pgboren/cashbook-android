package com.soleap.cashbook.common.view;

import android.content.Context;
import android.widget.TextView;

import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.value.ViewType;

public class ViewResourceStringTextFieldCreator extends ViewTextFieldCreator {

    public ViewResourceStringTextFieldCreator(ViewDataActivity context, ViewData fieldData) {
        super(context, fieldData);
    }

    @Override
    protected void setValue(TextView textValue, ViewData data) {
        viewSetterFactory.create(ViewType.TEXTVIEW, textValue).setString(ResourceUtil.getStringResourceByName(activity, data.getValue().toString()));
    }
}
