package com.soleap.cashbook.activity;

import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.document.Address;
import com.soleap.cashbook.document.Contact;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.document.Gender;


public class ContactAddNewActivity extends DocAddNewActivity {

    private TextInputEditText txtFirstname;
    private TextInputEditText txtLastname;
    private TextInputEditText txtNickname;
    private TextInputEditText txtPhonenumber1;
    private TextInputEditText txtPhonenumber2;
    private TextInputEditText txtPhonenumber3;
    private TextInputEditText txtFacebook;
    private TextInputEditText txtTelegram;

    private TextInputEditText txtHouseNo;
    private TextInputEditText txtFloor;
    private TextInputEditText txtRoomNumber;

    private TextInputEditText txtVillage;
    private TextInputEditText txtCommune;
    private TextInputEditText txtDistrict;
    private TextInputEditText txtProvince;
    private RadioGroup rdGender;

    private TextInputLayout inputLayoutFirstName;
    private TextInputLayout inputLayoutLastName;
    private TextInputLayout inputLayoutPhonenumber1;


    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_contact);
        initInputView();
    }

    @Override
    protected void initInputView() {
        inputLayoutFirstName = findViewById(R.id.inputLayout_first_name);
        inputLayoutLastName = findViewById(R.id.inputLayout_last_name);
        inputLayoutPhonenumber1 = findViewById(R.id.inputLayout_phoneNumber1);

        txtFirstname = findViewById(R.id.txt_first_name);
        txtLastname = findViewById(R.id.txt_last_name);
        txtNickname = findViewById(R.id.txt_nick_name);
        txtPhonenumber1 = findViewById(R.id.txt_phoneNumber1);
        txtPhonenumber2 = findViewById(R.id.txt_phoneNumber2);
        txtPhonenumber3 = findViewById(R.id.txt_phoneNumber3);
        txtFacebook = findViewById(R.id.txt_facebook);
        txtTelegram = findViewById(R.id.txt_telegram);

        txtHouseNo = findViewById(R.id.txt_houseNo);
        txtFloor = findViewById(R.id.txt_floor);
        txtRoomNumber = findViewById(R.id.txt_roomNumber);
        txtVillage = findViewById(R.id.txt_village);
        txtCommune = findViewById(R.id.txt_commune);
        txtDistrict = findViewById(R.id.txt_district);
        txtProvince = findViewById(R.id.txt_province);
        rdGender = findViewById(R.id.rdGender);

        txtFirstname.addTextChangedListener(this);
        txtLastname.addTextChangedListener(this);
        txtNickname.addTextChangedListener(this);
        txtPhonenumber1.addTextChangedListener(this);
        txtPhonenumber2.addTextChangedListener(this);
        txtPhonenumber3.addTextChangedListener(this);
        txtFacebook.addTextChangedListener(this);
        txtTelegram.addTextChangedListener(this);


        txtHouseNo.addTextChangedListener(this);
        txtFloor.addTextChangedListener(this);
        txtRoomNumber.addTextChangedListener(this);
        txtVillage.addTextChangedListener(this);
        txtCommune.addTextChangedListener(this);
        txtDistrict.addTextChangedListener(this);
        txtProvince.addTextChangedListener(this);

    }

    @Override
    protected void readInputData(Document document) {
        Contact contact = (Contact) document;
        contact.setFirstname(txtFirstname.getText().toString());
        contact.setLastname(txtLastname.getText().toString());
        contact.setNickname(txtNickname.getText().toString());
        contact.setPhoneNumber1(txtPhonenumber1.getText().toString());
        contact.setPhoneNumber2(txtPhonenumber2.getText().toString());
        contact.setPhoneNumber3(txtPhonenumber3.getText().toString());
        contact.setFacebook(txtFacebook.getText().toString());
        contact.setTelegram(txtTelegram.getText().toString());

        if (rdGender.getCheckedRadioButtonId() == R.id.radiomale) {
            contact.setGender(Gender.MALE);
        }

        if (rdGender.getCheckedRadioButtonId() == R.id.radiofemale) {
            contact.setGender(Gender.FEMALE);
        }

        Address address = new Address();
        address.setHouseNo(txtHouseNo.getText().toString());
        address.setFloor(txtFloor.getText().toString());
        address.setRoomNumber(txtRoomNumber.getText().toString());
        address.setVillage(txtVillage.getText().toString());
        address.setCommune(txtCommune.getText().toString());
        address.setDistrict(txtDistrict.getText().toString());
        address.setProvince(txtProvince.getText().toString());
        contact.setAddress(address);
    }

    @Override
    protected boolean validation() {
        isValid = true;

        if (txtFirstname.getText().toString().isEmpty()) {
            inputLayoutFirstName.setHelperText(getString(R.string.require));
            isValid = false;
        } else {
            inputLayoutFirstName.setHelperText("");
            isValid = true;
        }

        if (txtLastname.getText().toString().isEmpty()) {
            inputLayoutLastName.setHelperText(getString(R.string.require));
            isValid = false;
        } else {
            inputLayoutLastName.setHelperText("");
            isValid = true;
        }

        if (txtPhonenumber1.getText().toString().isEmpty()) {
            inputLayoutPhonenumber1.setHelperText(getString(R.string.require));
            isValid = false;
        } else {
            inputLayoutPhonenumber1.setHelperText("");
            isValid = true;
        }

        return super.validation();
    }
}
