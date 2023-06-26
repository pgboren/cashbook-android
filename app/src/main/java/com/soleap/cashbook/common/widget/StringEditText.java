package com.soleap.cashbook.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;

public class StringEditText extends BaseEditTextInputView<String> {

    public StringEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onTextChange(String text) {
        value = text;
        if (valueChangedListner != null) {
            valueChangedListner.onChanged(value, StringEditText.this.getId());
        }
    }

    @Override
    public boolean validate() {
        if (!errorEnabled) {
            return true;
        }
        return getValue() != null ? (getValue().toString().equals("")  ? false : true) : false;
    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        TextInputEditText editText = findViewById(R.id.editText);
        if (editText.getLineCount() > 1) {
            editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        }
    }
}
