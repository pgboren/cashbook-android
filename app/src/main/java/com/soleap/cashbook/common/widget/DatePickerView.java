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

import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerView extends LinearLayout {

    private Calendar value = Calendar.getInstance();
    private OnValueChanged onValueChangedListner = null;

    public void setOnValueChangedListner(OnValueChanged onValueChangedListner) {
        this.onValueChangedListner = onValueChangedListner;
    }

    public DatePickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DatePickerView, 0, 0);
        String title = a.getString(R.styleable.DatePickerView_dpv_title);
        boolean isRequire = a.getBoolean(R.styleable.DatePickerView_dpv_require, false);
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.date_picker, this, true);

        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout.setHint(title);
        textInputLayout.setErrorEnabled(isRequire);
        textInputLayout.setHelperText(context.getText(R.string.require));
        textInputLayout.setEndIconOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH,month);
                        cal.set(Calendar.DAY_OF_MONTH,day);
                        setValue(cal);
                    }
                };

                new DatePickerDialog(DatePickerView.this.getContext(), date, cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        setValue(value);
    }

    public void setValue(Calendar cal) {
        String myFormat="dd/MM/yyyy";
        TextView txtDate = findViewById(R.id.txt_value);
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        txtDate.setText(dateFormat.format(cal.getTime()));
        value = cal;
        if (onValueChangedListner != null) {
            onValueChangedListner.onChanged(cal);
        }
    }

    public Calendar getValue() {
        return value;
    }

}
