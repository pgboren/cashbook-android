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

public class ItemFormFragment extends DocFormFragment<Item> {
    private DocumentLookupEditText lkCategory;
    private DocumentLookupEditText lkAccount;
    private StringEditText textName;
    private StringEditText textDesc;
    private CurrencyEditText txtPrice;
    private CurrencyEditText txtCost;
    protected SwitchMaterial swEnable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.form_item_fragment, container, false);
        textName = view.findViewById(R.id.txt_name);
        lkCategory = view.findViewById(R.id.txt_category);
        lkAccount = view.findViewById(R.id.txt_account);
        textDesc = view.findViewById(R.id.txt_desc);
        txtPrice = view.findViewById(R.id.txt_price);
        txtCost = view.findViewById(R.id.txt_cost);
        lkCategory.setValueChangedListner(this);
        lkAccount.setValueChangedListner(this);
        textName.setValueChangedListner(this);
        txtCost.setValueChangedListner(this);
        txtPrice.setValueChangedListner(this);
        textDesc.setValueChangedListner(this);
        swEnable = (SwitchMaterial) view.findViewById(R.id.sw_enable);
        swEnable.setSelected(true);

        lkCategory.setActivity((AppCompatActivity) getActivity());
        lkAccount.setActivity((AppCompatActivity) getActivity());
        return  view;
    }

    @Override
    public void readInputData(Item item) {
        item.setCategory(lkCategory.getValue().getId());
        item.setAccount(lkAccount.getValue().getId());
        item.setName(textName.getValue());
        item.setPrice(txtPrice.getValue());
        item.setCost(txtCost.getValue());
        item.setDescription(textDesc.getValue());
        item.setEnable(swEnable.isChecked());
    }

    @Override
    public void assignValueToForm(Item document) {
        lkCategory.setvalueId(document.getCategory());
        lkAccount.setvalueId(document.getAccount());
        textName.setValue(document.getName());
        txtPrice.setValue(document.getPrice());
        txtCost.setValue(document.getCost());
        textDesc.setValue(document.getDescription());
        swEnable.setSelected(document.enable);
    }

    @Override
    public void resetFields() {
    }

    @Override
    public boolean validation() {
        isValid = textName.validate() && lkAccount.validate() && lkCategory.validate() && txtPrice.validate() && txtCost.validate();
        return isValid;
    }

}

