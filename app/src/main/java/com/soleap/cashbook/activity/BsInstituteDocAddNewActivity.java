package com.soleap.cashbook.activity;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocAddNewActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.document.Address;
import com.soleap.cashbook.document.Institute;
import com.soleap.cashbook.document.DocumentInfo;

public class BsInstituteDocAddNewActivity extends BsDocAddNewActivity {

    private static final String TAG = "BsInstituteDocAddNewActivity";

    @Override
    protected void readInputData(Document document) {
        super.readInputData(document);
        Institute institute = (Institute) document;
        Address address = new Address();
        address.setHouseNo(((TextInputEditText)findViewById(R.id.txt_houseNo)).getText().toString());
        address.setStreet(((TextInputEditText)findViewById(R.id.txt_street)).getText().toString());
        address.setVillage(((TextInputEditText)findViewById(R.id.txt_village)).getText().toString());
        address.setCommune(((TextInputEditText)findViewById(R.id.txt_commune)).getText().toString());
        address.setDistrict(((TextInputEditText)findViewById(R.id.txt_district)).getText().toString());
        address.setProvince(((TextInputEditText)findViewById(R.id.txt_province)).getText().toString());
        institute.setAddress(address);
    }

    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_institute);
        initInputView();
    }
}
