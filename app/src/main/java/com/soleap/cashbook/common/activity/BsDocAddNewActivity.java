package com.soleap.cashbook.common.activity;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.activity.DocAddNewActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.common.document.BsDocument;

public class BsDocAddNewActivity extends DocAddNewActivity {

    private static final String TAG = "BsDocListActivity";

    protected TextInputEditText txtName;
    protected SwitchMaterial swEnable;
    private TextInputLayout inputLayoutName;
    protected String documentName;

    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_bsdoc);
        initInputView();
    }

    @Override
    protected void readInputData(Document document) {
        BsDocument bsDocument = (BsDocument) document;
        bsDocument.setName(txtName.getText().toString());
        bsDocument.setEnable(swEnable.isChecked());
    }

    protected void initInputView() {
        inputLayoutName = (TextInputLayout)findViewById(R.id.inputLayout_name);
        swEnable = (SwitchMaterial) findViewById(R.id.sw_enable);
        swEnable.setSelected(true);
        txtName = findViewById(R.id.txt_name);
        txtName.addTextChangedListener(this);
    }

    @Override
    protected boolean validation() {

        if (txtName.getText().toString().isEmpty()) {
            inputLayoutName.setHelperText(getString(R.string.require));
            isValid = false;
        } else {
            inputLayoutName.setHelperText("");
            isValid = true;
        }
        return super.validation();
    }

}
