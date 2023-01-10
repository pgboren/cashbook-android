package com.soleap.cashbook.activity;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.document.DocumentInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DealAddNewActivity extends DocAddNewActivity {

    final Calendar calendar = Calendar.getInstance();
    public static int RESULT_GENERRAL = 2001;
    private TextView txtDate;

    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_deal);
        initInputView();
    }

    @Override
    protected void initInputView() {
        txtDate = findViewById(R.id.txt_date);

        View btnDate = findViewById(R.id.btn_deal_form_date);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DAY_OF_MONTH,day);
                        updateDateLabel();
                    }
                };
                new DatePickerDialog(DealAddNewActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        updateDateLabel();
    }

    private void updateDateLabel() {
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        txtDate.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    protected void readInputData(Document document) {

    }

    @Override
    protected boolean validation() {
        isValid = true;



        return super.validation();
    }
}