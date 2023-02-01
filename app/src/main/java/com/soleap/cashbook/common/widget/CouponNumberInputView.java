package com.soleap.cashbook.common.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CouponNumberInputView extends LinearLayout {

    private String value = null;

    public CouponNumberInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CouponNumberInputView, 0, 0);
        String title = a.getString(R.styleable.CouponNumberInputView_cnv_title);
        String blankMessage = a.getString(R.styleable.CouponNumberInputView_cnv_blank_message);
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.coupon_number_input_view, this, true);
        TextView textTitle = findViewById(R.id.title);
        textTitle.setText(title);

        setValue(value);
    }

    public void setValue(String number) {
        value = number;
    }

    public String getValue() {
        return value;
    }
}
