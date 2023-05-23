package com.soleap.cashbook.activity;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocEditActivity;
import com.soleap.cashbook.document.Address;
import com.soleap.cashbook.common.document.BsDocument;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.document.Institute;

public class BsInstituteDocEditActivity extends BsDocEditActivity {

    private static final String TAG = "BsInstituteDocAddNewActivity";

    @Override
    protected void readInputData(BsDocument document) {
        super.readInputData(document);
        Institute institute =  (Institute)document;
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
        this.documentName = getIntent().getExtras().getString(DocumentName.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_institute);
        initInputView();
    }

    @Override
    protected void assignValueToForm(BsDocument document) {
        super.assignValueToForm(document);
        Address address = ((Institute) document).getAddress();
        ((TextInputEditText)findViewById(R.id.txt_houseNo)).setText(address.getHouseNo());
        ((TextInputEditText)findViewById(R.id.txt_street)).setText(address.getStreet());
        ((TextInputEditText)findViewById(R.id.txt_village)).setText(address.getVillage());
        ((TextInputEditText)findViewById(R.id.txt_commune)).setText(address.getCommune());
        ((TextInputEditText)findViewById(R.id.txt_district)).setText(address.getDistrict());
        ((TextInputEditText)findViewById(R.id.txt_province)).setText(address.getProvince());
    }
}
