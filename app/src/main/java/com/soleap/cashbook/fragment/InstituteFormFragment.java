package com.soleap.cashbook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.BsDocument;
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.common.widget.OnValueChangedListner;
import com.soleap.cashbook.common.widget.StringEditText;
import com.soleap.cashbook.document.Institute;

public class InstituteFormFragment extends DocFormFragment<Institute> {

    private View view;
    protected StringEditText txtName;

    protected StringEditText txtAddress;
    protected SwitchMaterial swEnable;
    private TextInputLayout inputLayoutName;

    private TextInputLayout inputLayoutAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.form_institute_fragment, container, false);
        inputLayoutName = (TextInputLayout)view.findViewById(R.id.inputLayout_name);
        inputLayoutAddress = view.findViewById(R.id.inputLayout_address);
        swEnable = (SwitchMaterial) view.findViewById(R.id.sw_enable);
        swEnable.setSelected(true);
        txtName = view.findViewById(R.id.txt_name);
        txtAddress = view.findViewById(R.id.txt_address);
        txtName.setValueChangedListner(this);
        txtAddress.setValueChangedListner(this);
        return  view;
    }

    @Override
    public void readInputData(Institute document) {
        document.setName(txtName.getValue());
        document.setAddress(txtAddress.getValue());
        document.setEnable(swEnable.isChecked());
    }

    @Override
    public void assignValueToForm(Institute document) {

    }

    @Override
    public void resetFields() {

    }

    @Override
    public boolean validation() {
        isValid = txtName.validate() && txtAddress.validate();
        return isValid;
    }

}
