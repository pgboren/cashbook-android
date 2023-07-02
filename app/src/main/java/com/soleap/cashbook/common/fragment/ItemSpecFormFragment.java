package com.soleap.cashbook.common.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.widget.CurrencyEditText;
import com.soleap.cashbook.common.widget.StringEditText;
import com.soleap.cashbook.common.widget.lookup.DocumentLookupEditText;
import com.soleap.cashbook.document.Item;
import com.soleap.cashbook.document.ItemSpecification;

public class ItemSpecFormFragment extends DocFormFragment<ItemSpecification> {
    private StringEditText textName;
    private StringEditText textValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.form_item_spec_fragment, container, false);
        textName = view.findViewById(R.id.txt_name);
        textValue = view.findViewById(R.id.txt_value);
        textName.setValueChangedListner(this);
        textValue.setValueChangedListner(this);
        return  view;
    }

    @Override
    public void readInputData(ItemSpecification item) {

    }

    @Override
    public void assignValueToForm(ItemSpecification document) {

    }

    @Override
    public void resetFields() {
    }

    @Override
    public boolean validation() {
        return isValid;
    }

}

