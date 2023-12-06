package com.soleap.cashbook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    private TextInputEditText txtPrimaryNumber;

    private TextInputEditText txtSecondaryNumber;
    private TextInputEditText txtFacebook;
    private TextInputEditText txtTelegram;

    private TextInputEditText txtAddress;

    private RadioGroup rdGender;
    private TextInputLayout inputLayoutFirstName;
    private TextInputLayout inputLayoutPhonenumber;
    private TextInputEditText txtFirstRelativeType;
    private AutoCompleteTextView secondRelativeType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inputFormView = inflater.inflate(R.layout.form_contact_fragment, container, false);

        inputLayoutFirstName = inputFormView.findViewById(R.id.inputLayout_name);
        inputLayoutPhonenumber = inputFormView.findViewById(R.id.inputLayout_phonenumber_first);
        txtName = inputFormView.findViewById(R.id.txt_name);

        txtPrimaryNumber = inputFormView.findViewById(R.id.txt_phonenumber_first);
        txtSecondaryNumber = inputFormView.findViewById(R.id.txt_phoneNumber_second);
        txtAddress = inputFormView.findViewById(R.id.txt_address);
        txtFacebook = inputFormView.findViewById(R.id.txt_facebook);
        txtTelegram = inputFormView.findViewById(R.id.txt_telegram);
        txtName.addTextChangedListener(this);
        txtPrimaryNumber.addTextChangedListener(this);
        txtFacebook.addTextChangedListener(this);
        txtTelegram.addTextChangedListener(this);

        return inputFormView;
    }

    @Override
    public void readInputData(Contact document) {
        Contact contact = (Contact) document;
        contact.setType("CUS");
        contact.setName(txtName.getText().toString());
        contact.setPrimaryNumber(txtPrimaryNumber.getText().toString());
        contact.setSecondaryNumber(txtSecondaryNumber.getText().toString());
        contact.setFacebook(txtFacebook.getText().toString());
        contact.setTelegram(txtTelegram.getText().toString());
        contact.setAddress(txtAddress.getText().toString());

    }

    @Override
    public void assignValueToForm(Contact document) {
        txtName.setText(document.getName());
        txtPrimaryNumber.setText(document.getPrimaryNumber());
        txtSecondaryNumber.setText(document.getSecondaryNumber());
        txtFacebook.setText(document.getFacebook());
        txtTelegram.setText(document.getTelegram());
        txtAddress.setText(document.getAddress());
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

        if (txtPrimaryNumber.getText().toString().isEmpty()) {
            inputLayoutPhonenumber.setHelperText(getString(R.string.require));
            isValid = false;
        } else {
            inputLayoutPhonenumber.setHelperText("");
            isValid = true;
        }

        return isValid;
    }
}
