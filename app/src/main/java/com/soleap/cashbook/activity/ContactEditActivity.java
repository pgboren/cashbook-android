package com.soleap.cashbook.activity;

import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.EditRestApiActivity;
import com.soleap.cashbook.document.Address;
import com.soleap.cashbook.document.Contact;

import org.json.JSONException;
import org.json.JSONObject;

public class ContactEditActivity extends EditRestApiActivity<Contact> implements TextWatcher {

    private TextInputEditText txtName;
    private TextInputEditText txtLatinName;
    private TextInputEditText txtNickname;
    private TextInputEditText txtPhonenumber1;
    private TextInputEditText txtPhonenumber2;
    private TextInputEditText txtPhonenumber3;
    private TextInputEditText txtFacebook;
    private TextInputEditText txtTelegram;
    private TextInputEditText txtVillage;
    private TextInputEditText txtCommune;
    private TextInputEditText txtDistrict;
    private TextInputEditText txtProvince;

    @Override
    protected void assignValueToForm(Contact contact) {
        txtName.setText(contact.getName());
        txtLatinName.setText(contact.getLatinname());
        txtNickname.setText(contact.getNickname());
        txtPhonenumber1.setText(contact.getPhoneNumber1());
        txtPhonenumber2.setText(contact.getPhoneNumber2());
        txtPhonenumber3.setText(contact.getPhoneNumber3());
        txtFacebook.setText(contact.getFacebook());
        txtTelegram.setText(contact.getTelegram());
        txtVillage.setText(contact.getAddress().getVillage());
        txtCommune.setText(contact.getAddress().getCommune());
        txtDistrict.setText(contact.getAddress().getDistrict());
        txtProvince.setText(contact.getAddress().getProvince());

        txtName.addTextChangedListener(this);
        txtLatinName.addTextChangedListener(this);
        txtNickname.addTextChangedListener(this);
        txtPhonenumber1.addTextChangedListener(this);
        txtPhonenumber2.addTextChangedListener(this);
        txtPhonenumber3.addTextChangedListener(this);
        txtFacebook.addTextChangedListener(this);
        txtTelegram.addTextChangedListener(this);
        txtVillage.addTextChangedListener(this);
        txtCommune.addTextChangedListener(this);
        txtDistrict.addTextChangedListener(this);
        txtProvince.addTextChangedListener(this);
    }

    @Override
    protected void setViewContent() {
        this.setContentView(R.layout.activity_form_contact);
        txtName = findViewById(R.id.txt_first_name);
        txtLatinName = findViewById(R.id.txt_last_name);
        txtNickname = findViewById(R.id.txt_nick_name);
        txtPhonenumber1 = findViewById(R.id.txt_phoneNumber1);
        txtPhonenumber2 = findViewById(R.id.txt_phoneNumber2);
        txtPhonenumber3 = findViewById(R.id.txt_phoneNumber3);
        txtFacebook = findViewById(R.id.txt_facebook);
        txtTelegram = findViewById(R.id.txt_telegram);
        txtVillage = findViewById(R.id.txt_village);
        txtCommune = findViewById(R.id.txt_commune);
        txtDistrict = findViewById(R.id.txt_district);
        txtProvince = findViewById(R.id.txt_province);
    }

    @Override
    protected void onError(JSONObject errorObject) throws JSONException {
    }

    @Override
    protected boolean validation() {
        btnSave.setEnabled(true);
        return true;
    }

    @Override
    protected void readInputData(Contact document) {
        document.setName(txtName.getText().toString());
        document.setLatinname(txtLatinName.getText().toString());
        document.setNickname(txtNickname.getText().toString());
        document.setPhoneNumber1(txtPhonenumber1.getText().toString());
        document.setPhoneNumber2(txtPhonenumber2.getText().toString());
        document.setPhoneNumber3(txtPhonenumber3.getText().toString());
        document.setFacebook(txtFacebook.getText().toString());
        document.setTelegram(txtTelegram.getText().toString());
        Address address = new Address();
        address.setVillage(txtVillage.getText().toString());
        address.setCommune(txtCommune.getText().toString());
        address.setDistrict(txtDistrict.getText().toString());
        address.setProvince(txtProvince.getText().toString());
        document.setAddress(address);
    }

    @Override
    protected void resetFields() {

    }



}
