package com.soleap.cashbook.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.widget.DatePickerView;
import com.soleap.cashbook.common.widget.input.BaseButtomSheetInputView;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatePickerInputView extends BaseButtomSheetInputView<Calendar> {

    public DatePickerInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        final Calendar cal = Calendar.getInstance();
        setValue(cal);
    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
    }

    @Override
    protected void onClick() {
        showDatePickerDialog();
    }

    @Override
    protected void onValueSet() {

    }

    @Override
    protected void updateDisplayValue() {
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        txtValue.setText(dateFormat.format(getValue().getTime()));
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

        new DatePickerDialog(DatePickerInputView.this.getContext(), date, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show();
    }

}
