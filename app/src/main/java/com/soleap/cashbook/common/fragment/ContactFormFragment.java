package com.soleap.cashbook.common.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.document.Contact;
import com.soleap.cashbook.document.Gender;

public class ContactFormFragment extends DocFormFragment<Contact> {

    private View view;
    private TextInputEditText txtName;
    private TextInputEditText txtLatinName;
    private TextInputEditText txtNickname;
    private TextInputEditText txtPhoneNumber;
    private TextInputEditText txtFacebook;
    private TextInputEditText txtTelegram;

    private TextInputEditText txtAddress;

    private RadioGroup rdGender;

    private TextInputLayout inputLayoutFirstName;
    private TextInputLayout inputLayoutLastName;
    private TextInputLayout inputLayoutPhonenumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.form_contact_fragment, container, false);
        inputLayoutFirstName = view.findViewById(R.id.inputLayout_latin_name);
        inputLayoutLastName = view.findViewById(R.id.inputLayout_name);
        inputLayoutPhonenumber = view.findViewById(R.id.inputLayout_phoneNumber);
        txtName = view.findViewById(R.id.txt_latin_name);
        txtLatinName = view.findViewById(R.id.txt_name);
        txtNickname = view.findViewById(R.id.txt_nick_name);
        txtPhoneNumber = view.findViewById(R.id.txt_phoneNumber);
        txtAddress = view.findViewById(R.id.txt_address);
        txtFacebook = view.findViewById(R.id.txt_facebook);
        txtTelegram = view.findViewById(R.id.txt_telegram);
        rdGender = view.findViewById(R.id.rdGender);
        txtName.addTextChangedListener(this);
        txtLatinName.addTextChangedListener(this);
        txtNickname.addTextChangedListener(this);
        txtPhoneNumber.addTextChangedListener(this);
        txtFacebook.addTextChangedListener(this);
        txtTelegram.addTextChangedListener(this);
        return  view;
    }

    @Override
    public void readInputData(Contact document) {
        Contact contact = (Contact) document;
        contact.setName(txtName.getText().toString());
        contact.setLatinname(txtLatinName.getText().toString());
        contact.setNickname(txtNickname.getText().toString());
        contact.setPhoneNumber(txtPhoneNumber.getText().toString());
        contact.setFacebook(txtFacebook.getText().toString());
        contact.setTelegram(txtTelegram.getText().toString());
        contact.setAddress(txtAddress.getText().toString());

        if (rdGender.getCheckedRadioButtonId() == R.id.radiomale) {
            contact.setGender(Gender.MALE);
        }

        if (rdGender.getCheckedRadioButtonId() == R.id.radiofemale) {
            contact.setGender(Gender.FEMALE);
        }

    }

    @Override
    public void assignValueToForm(Contact document) {
        txtName.setText(document.getName());
        txtLatinName.setText(document.getLatinname());
        txtNickname.setText(document.getNickname());
        txtPhoneNumber.setText(document.getPhoneNumber());
        txtFacebook.setText(document.getFacebook());
        txtTelegram.setText(document.getTelegram());
        txtAddress.setText(document.getAddress());

        if (document.getGender().equals(Gender.MALE)) {
            rdGender.check(R.id.radiomale);
        }

        if (document.getGender().equals(Gender.FEMALE)) {
            rdGender.check(R.id.radiofemale);
        }

    }

    @Override
    public void resetFields() {

    }

    @Override
    public boolean validation() {
        isValid = true;

        if (txtName.getText().toString().isEmpty()) {
            inputLayoutFirstName.setHelperText(getString(R.string.require));
            isValid = false;
        } else {
            inputLayoutFirstName.setHelperText("");
            isValid = true;
        }

        if (txtLatinName.getText().toString().isEmpty()) {
            inputLayoutLastName.setHelperText(getString(R.string.require));
            isValid = false;
        } else {
            inputLayoutLastName.setHelperText("");
            isValid = true;
        }

        if (txtPhoneNumber.getText().toString().isEmpty()) {
            inputLayoutPhonenumber.setHelperText(getString(R.string.require));
            isValid = false;
        } else {
            inputLayoutPhonenumber.setHelperText("");
            isValid = true;
        }

        return isValid;
    }
}
