package com.soleap.cashbook.common.fragment;

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

public class BSDocFormFragment extends DocFormFragment<BsDocument> {

    private View view;
    protected TextInputEditText txtName;
    protected SwitchMaterial swEnable;
    private TextInputLayout inputLayoutName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.form_bs_doc_fragment, container, false);
        inputLayoutName = (TextInputLayout)view.findViewById(R.id.inputLayout_name);
        swEnable = (SwitchMaterial) view.findViewById(R.id.sw_enable);
        swEnable.setSelected(true);
        txtName = view.findViewById(R.id.txt_name);
        txtName.addTextChangedListener(this);
        return  view;
    }

    @Override
    public void readInputData(BsDocument document) {
        document.setName(txtName.getText().toString());
        document.setEnable(swEnable.isChecked());
    }

    @Override
    public void assignValueToForm(BsDocument document) {
        txtName.setText(document.getName());
        swEnable.setSelected(document.enable);
    }

    @Override
    public void resetFields() {
        txtName.getText().clear();
        swEnable.setSelected(false);
    }

    @Override
    public boolean validation() {
        if (txtName.getText().toString().isEmpty()) {
            inputLayoutName.setHelperText(getString(R.string.require));
            isValid = false;
        } else {
            inputLayoutName.setHelperText("");
            isValid = true;
        }
        return isValid;
    }
}
