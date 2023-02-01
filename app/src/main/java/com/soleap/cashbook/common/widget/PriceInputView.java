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

public class PriceInputView extends LinearLayout {

    private double value;

    public PriceInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PriceInputView, 0, 0);
        String title = a.getString(R.styleable.PriceInputView_piv_title);
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_input_price, this, true);
        TextView textTitle = findViewById(R.id.title);
        textTitle.setText(title);

    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
