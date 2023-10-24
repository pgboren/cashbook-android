package com.soleap.cashbook.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.DatePicker;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.util.NumberUtils;
import com.soleap.cashbook.common.widget.input.BaseButtomSheetInputView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NumberInputView extends BaseButtomSheetInputView<Double> {

    private String format;

    public NumberInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setValue((double) 0);
    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        this.format = a.getString(R.styleable.BaseButtomSheetInputView_biv_format);
    }

    @Override
    protected void onClick() {

    }

    @Override
    protected void onValueSet() {

    }

    @Override
    protected void updateDisplayValue() {
        txtValue.setText(NumberUtils.formatDouble(format, getValue().doubleValue()));
    }

}
