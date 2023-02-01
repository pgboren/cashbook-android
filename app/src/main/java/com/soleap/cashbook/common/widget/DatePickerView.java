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

public class DatePickerView extends LinearLayout {

    private Calendar value = Calendar.getInstance();

    public DatePickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DatePickerView, 0, 0);
        String title = a.getString(R.styleable.DatePickerView_dpv_title);
        String blankMessage = a.getString(R.styleable.DatePickerView_dpv_blank_message);
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.date_picker, this, true);
        TextView textTitle = findViewById(R.id.title);
        textTitle.setText(title);

        TextView txtDate = findViewById(R.id.txt_value);
        View btnEndIcon = findViewById(R.id.btn_end_icon);
        btnEndIcon.setOnClickListener(new View.OnClickListener() {
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
    }

    public Calendar getValue() {
        return value;
    }

}
