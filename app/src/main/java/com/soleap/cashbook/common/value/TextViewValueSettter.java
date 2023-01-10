package com.soleap.cashbook.common.value;

import android.content.Context;
import android.icu.text.NumberFormat;
import android.icu.util.Currency;
import android.view.View;
import android.widget.TextView;

import com.soleap.cashbook.common.util.DateUtils;

import java.util.Date;
import java.util.Locale;

public final class TextViewValueSettter extends ViewValueSettter {

    private TextView textView;
    private View parent;
    private Context context;


    public TextViewValueSettter(View parent, TextView textView) {
        this.parent = parent;
        this.context = parent.getContext();
        this.textView =  textView;

    }

    public TextViewValueSettter(View parent, int id) {
        this.parent = parent;
        this.context = parent.getContext();
        textView = parent.findViewById(id);
    }

    @Override
    public void setLong(long value) {
        textView.setText(String.valueOf(value));
    }

    @Override
    public void setLong(long value, String format) {
        textView.setText(String.format(format, value));
    }

    @Override
    public void setDouble(double value, String format) {
        textView.setText(String.valueOf(value));
    }

    @Override
    public void setDate(long value, String format) {
        setDate(new Date(value), format);
    }

    @Override
    public void setDate(Date value, String format) {
        textView.setText(DateUtils.format(value, format));
    }

    @Override
    public void setString(String value) {
        textView.setText(value);
    }

    @Override
    public void setCurrency(double value, Locale locale) {
        NumberFormat formater = NumberFormat.getCurrencyInstance(locale);
        formater.setCurrency(Currency.getInstance(locale));
        textView.setText(formater.format(value));
    }
}
