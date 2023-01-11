package com.soleap.cashbook.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocListActivity;
import com.soleap.cashbook.common.activity.BsDocLookUpActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.document.DocumentInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SaleInstallmentPaymentRequestAddNewActivity extends DocAddNewActivity {

    public final static int SELECT_BRANCH_LOOKUP_REQUEST_CODE = 201;

    final Calendar calendar = Calendar.getInstance();
    public static int RESULT_GENERRAL = 2001;
    private TextView txtDate;

    protected String getViewTitle() {
        return "nav_menu_installment_payment_sale_request";
    }

    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_deal);
        initInputView();
        setTitle(getString(R.string.nav_menu_installment_payment_sale_request));
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
                new DatePickerDialog(SaleInstallmentPaymentRequestAddNewActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        initLookupButton(R.id.btn_deal_form_branch, BsDocLookUpActivity.LOOK_UP_BRANCH_REQUEST_CODE, DocumentInfo.BRANCH);
        initLookupButton(R.id.btn_deal_form_institute, BsDocLookUpActivity.LOOK_UP_INSTITUTE_REQUEST_CODE, DocumentInfo.INSTITUE);
        initLookupButton(R.id.btn_deal_form_item, BsDocLookUpActivity.LOOK_UP_ITEM_REQUEST_CODE, DocumentInfo.ITEM);
        initLookupButton(R.id.btn_deal_form_customer, BsDocLookUpActivity.LOOK_UP_CONTACT_REQUEST_CODE, DocumentInfo.CONTACT);
        updateDateLabel();
    }

    private void initLookupButton(int buttonId, final int requestCode, final String documentName) {
        View btnBranch = findViewById(buttonId);
        btnBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaleInstallmentPaymentRequestAddNewActivity.this, BsDocLookUpActivity.class);
                intent.putExtra(DocumentInfo.DOCUMENT_NAME, documentName);
                startActivityForResult(intent, requestCode);
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BsDocLookUpActivity.LOOK_UP_BRANCH_REQUEST_CODE) {
            DocumentSnapshot doc = (DocumentSnapshot) data.getSerializableExtra(BsDocLookUpActivity.LOOK_UP_DOCUMENT);
//            String name = doc.getDataValue("category").getDataValue("name").getValue().toString();

        }
    }
}