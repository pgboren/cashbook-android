package com.soleap.cashbook.common.view;

import android.content.Context;
import android.widget.TextView;

import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.value.ViewType;

import java.util.Locale;

public class ViewCurrencyFieldCreator extends ViewTextFieldCreator {

    public ViewCurrencyFieldCreator(ViewDataActivity context, ViewData fieldData) {
        super(context, fieldData);
    }

    @Override
    protected void setValue(TextView textValue, ViewData data) {
        viewSetterFactory.create(ViewType.TEXTVIEW, textValue).setCurrency((Double)data.getValue(), Locale.forLanguageTag(data.getLocale()));
    }

}
