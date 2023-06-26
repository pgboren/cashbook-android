package com.soleap.cashbook.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;

public class IntegerEditText extends BaseEditTextInputView<Integer> {

    public IntegerEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onTextChange(String text) {
        if (!text.isEmpty()) {
            value = Integer.parseInt(text);
        }
        if (valueChangedListner != null) {
            valueChangedListner.onChanged(value, IntegerEditText.this.getId());
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
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }
}
