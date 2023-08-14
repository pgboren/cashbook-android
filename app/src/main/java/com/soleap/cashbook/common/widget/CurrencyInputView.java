package com.soleap.cashbook.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;

public class CurrencyInputView extends BaseInputView<String> {


    public CurrencyInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    protected int getInputLayout() {
        return R.layout.input_currency_view;
    }

    @Override
    protected void renderView() {

    }
}
