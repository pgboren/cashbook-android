package com.soleap.cashbook.common.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerView extends BaseTextInputView<Calendar> {

    public DatePickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.value = Calendar.getInstance();
        editText.setTextIsSelectable(false);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }

    private void showDatePickerDialog() {
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

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        textInputLayout.setEndIconDrawable(R.drawable.ic_circle_calendar);
        textInputLayout.setEndIconOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    @Override
    protected void onTextChange(String text) {

    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void setValue(Calendar value) {
        this.value = value;
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(value.getTime()));
        if (valueChangedListner != null) {
            valueChangedListner.onChanged(value, DatePickerView.this.getId());
        }
    }

}
