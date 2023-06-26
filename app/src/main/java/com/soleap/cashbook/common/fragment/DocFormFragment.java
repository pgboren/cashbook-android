package com.soleap.cashbook.common.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.widget.OnValueChangedListner;

public abstract class DocFormFragment<T extends Document> extends Fragment implements TextWatcher, OnValueChangedListner {

    protected boolean isValid = false;

    protected View view;

    protected ValueChangeListner valueChangeListner;

    public void setValueChangeListner(ValueChangeListner valueChangeListner) {
        this.valueChangeListner = valueChangeListner;
    }

    public abstract void readInputData(T document);

    public abstract void assignValueToForm(T document);

    public abstract void resetFields();

    public abstract boolean validation();

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        onValueChanged();
    }

    protected void onValueChanged() {
        if (valueChangeListner != null) {
            valueChangeListner.onValueChanged();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onChanged(Object value, int viewId) {
        onValueChanged();
    }

    public interface ValueChangeListner {
        void onValueChanged();
    }


}
