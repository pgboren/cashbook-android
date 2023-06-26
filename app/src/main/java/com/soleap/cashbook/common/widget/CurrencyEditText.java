package com.soleap.cashbook.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
public class CurrencyEditText extends BaseEditTextInputView<Double> {

    private static String TAG = "CurrencyEditText";

    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        float defaltValue = a.getFloat(R.styleable.EditTextInputView_ev_default, 0);
        TextInputEditText editText = findViewById(R.id.editText);
        editText.setText(String.valueOf(defaltValue));
    }

    public CurrencyEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TextInputEditText editText = findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }


    @Override
    protected void onTextChange(String text) {
        try {
            value = Double.parseDouble(text);
            if (valueChangedListner != null) {
                valueChangedListner.onChanged(value, CurrencyEditText.this.getId());
            }
        }
        catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
            value = null;
            if (valueChangedListner != null) {
                valueChangedListner.onChanged(null, CurrencyEditText.this.getId());
            }
        }
    }

    public boolean validate() {
        if (!errorEnabled) {
            return true;
        }
        return getValue() != null ? (getValue().toString().equals("")  ? false : true) : false;
    }
}
