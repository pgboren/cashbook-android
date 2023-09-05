package com.soleap.cashbook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.common.widget.StringEditText;
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
    public void readInputData(ItemSpecification itemSpec) {
        itemSpec.setItem(getDocId());
        itemSpec.setName(textName.getValue());
        itemSpec.setValue(textValue.getValue());
    }

    @Override
    public void assignValueToForm(ItemSpecification itemSpec) {
        textName.setValue(itemSpec.getName());
        textValue.setValue(itemSpec.getValue());
    }

    @Override
    public void resetFields() {
    }

    @Override
    public boolean validation() {
        return isValid;
    }

}

