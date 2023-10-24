package com.soleap.cashbook.fragment;

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
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.document.Contact;
import com.soleap.cashbook.document.Gender;

public class ContactFormFragment extends DocFormFragment<Contact> {

    private View inputFormView;
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
        inputFormView = inflater.inflate(R.layout.form_contact_fragment, container, false);
        inputLayoutFirstName = inputFormView.findViewById(R.id.inputLayout_latin_name);
        inputLayoutLastName = inputFormView.findViewById(R.id.inputLayout_name);
        inputLayoutPhonenumber = inputFormView.findViewById(R.id.inputLayout_phoneNumber);
        txtName = inputFormView.findViewById(R.id.txt_name);
        txtLatinName = inputFormView.findViewById(R.id.txt_latin_name);
        txtNickname = inputFormView.findViewById(R.id.txt_nick_name);
        txtPhoneNumber = inputFormView.findViewById(R.id.txt_phoneNumber);
        txtAddress = inputFormView.findViewById(R.id.txt_address);
        txtFacebook = inputFormView.findViewById(R.id.txt_facebook);
        txtTelegram = inputFormView.findViewById(R.id.txt_telegram);
        rdGender = inputFormView.findViewById(R.id.rdGender);
        txtName.addTextChangedListener(this);
        txtLatinName.addTextChangedListener(this);
        txtNickname.addTextChangedListener(this);
        txtPhoneNumber.addTextChangedListener(this);
        txtFacebook.addTextChangedListener(this);
        txtTelegram.addTextChangedListener(this);
        return inputFormView;
    }

    @Override
    public void readInputData(Contact document) {
        Contact contact = (Contact) document;
        contact.setType("CUS");
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
        if (document.getType().equals("CUS")) {
            if (document.getGender() != null && document.getGender().equals(Gender.MALE)) {
                rdGender.check(R.id.radiomale);
            }
            if (document.getGender() != null && document.getGender().equals(Gender.FEMALE)) {
                rdGender.check(R.id.radiofemale);
            }
        }
        else {
            inputFormView.findViewById(R.id.gender_container).setVisibility(View.GONE);
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
