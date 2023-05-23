package com.soleap.cashbook.widget.dialog;

import android.view.View;
import android.widget.EditText;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.widget.dialog.AddNewDocumentInputDialog;
import com.soleap.cashbook.document.Address;
import com.soleap.cashbook.document.Contact;
import com.soleap.cashbook.document.DocumentName;

public class ContactInputDialog extends AddNewDocumentInputDialog<Contact> {

    public ContactInputDialog(int viewLayout) {
        super(viewLayout, DocumentName.CONTACT);
    }

    @Override
    protected Contact readValueFromForm(View view) {
        Contact contact = new Contact();
        EditText txtName = view.findViewById(R.id.txt_name);
        EditText txtLatinName = view.findViewById(R.id.txt_latin_name);
        EditText txtNickName = view.findViewById(R.id.txt_nick_name);
        EditText txtPhonenumber1 = view.findViewById(R.id.txt_phoneNumber1);
        EditText txtPhonenumber2 = view.findViewById(R.id.txt_phoneNumber2);
        EditText txtPhonenumber3 = view.findViewById(R.id.txt_phoneNumber3);
        EditText txtFacebook = view.findViewById(R.id.txt_facebook);
        EditText txtTelegram = view.findViewById(R.id.txt_telegram);
        contact.setName(txtName.getText().toString());
        contact.setLatinname(txtLatinName.getText().toString());
        contact.setNickname(txtNickName.getText().toString());
        contact.setPhoneNumber1(txtPhonenumber1.getText().toString());
        contact.setPhoneNumber2(txtPhonenumber2.getText().toString());
        contact.setPhoneNumber3(txtPhonenumber3.getText().toString());
        contact.setFacebook(txtFacebook.getText().toString());
        contact.setTelegram(txtTelegram.getText().toString());

        Address address = new Address();
        EditText txtHouseNo = view.findViewById(R.id.txt_houseNo);
        EditText txtFloorNo = view.findViewById(R.id.txt_floor);
        EditText txtRoomNo = view.findViewById(R.id.txt_roomNumber);
        EditText txtStree = view.findViewById(R.id.txt_street);
        EditText txtVillage = view.findViewById(R.id.txt_village);
        EditText txtCommune = view.findViewById(R.id.txt_commune);
        EditText txtDistrict = view.findViewById(R.id.txt_district);
        EditText txtProvince = view.findViewById(R.id.txt_province);
        address.setHouseNo(txtHouseNo.getText().toString());
        address.setFloor(txtFloorNo.getText().toString());
        address.setRoomNumber(txtRoomNo.getText().toString());
        address.setStreet(txtStree.getText().toString());
        address.setVillage(txtVillage.getText().toString());
        address.setCommune(txtCommune.getText().toString());
        address.setDistrict(txtDistrict.getText().toString());
        address.setProvince(txtProvince.getText().toString());
        contact.setAddress(address);
        return contact;
    }

    @Override
    protected void assignValueToForm(Contact value) {

    }

}