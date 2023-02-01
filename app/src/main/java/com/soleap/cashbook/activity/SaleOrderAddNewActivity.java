package com.soleap.cashbook.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocLookUpActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.document.DocumentInfo;

import java.util.Calendar;

public class SaleOrderAddNewActivity extends DocAddNewActivity {

    public final static int SELECT_BRANCH_LOOKUP_REQUEST_CODE = 201;

    final Calendar calendar = Calendar.getInstance();
    private DocumentSnapshot branch;

    public static int RESULT_GENERRAL = 2001;
    private TextView txtDate;
    public String label = "Customer";

    protected String getViewTitle() {
        return "nav_menu_installment_payment_sale_request";
    }

    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_sale_order);
        initInputView();
        setTitle(getString(R.string.nav_menu_installment_payment_sale_request));
    }

    @Override
    protected void initInputView() {

    }

    private void initLookupButton(int buttonId, final int requestCode, final String documentName) {
        final View button = findViewById(buttonId);
        View btnCancel = button.findViewById(R.id.imv_cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaleOrderAddNewActivity.this, BsDocLookUpActivity.class);
                intent.putExtra(DocumentInfo.DOCUMENT_NAME, documentName);
                startActivityForResult(intent, requestCode);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.findViewById(R.id.value_view_container).setVisibility(View.GONE);
                button.findViewById(R.id.layout_blank_value).setVisibility(View.VISIBLE);
            }
        });

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
    }

    private void onDocumentSelected(int layoutId, DocumentSnapshot doc) {
        View docLayout = findViewById(layoutId);
        View selectedLayout = docLayout.findViewById(R.id.value_view_container);
        View blankLayout = docLayout.findViewById(R.id.layout_blank_value);
        TextView txtName = docLayout.findViewById(R.id.txt_name);
        txtName.setText(doc.getDataValue("name").getValue().toString());
        selectedLayout.setVisibility(View.VISIBLE);
        blankLayout.setVisibility(View.GONE);
    }
}