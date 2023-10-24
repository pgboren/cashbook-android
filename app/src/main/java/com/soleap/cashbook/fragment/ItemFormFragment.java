package com.soleap.cashbook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.soleap.cashbook.Global;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.common.widget.BarcodeStringEditText;
import com.soleap.cashbook.common.widget.CurrencyEditText;
import com.soleap.cashbook.common.widget.IntegerEditText;
import com.soleap.cashbook.common.widget.StringEditText;
import com.soleap.cashbook.common.widget.doclookup.DocumentLookupInputView;
import com.soleap.cashbook.common.widget.lookup.TextEnumSheetLookUpInputView;
import com.soleap.cashbook.document.Item;
import com.soleap.cashbook.document.Vehicle;

public class ItemFormFragment extends DocFormFragment<Vehicle> {
    private DocumentLookupInputView lkCategory;

    private DocumentLookupInputView lkColor;
    private DocumentLookupInputView lkAccount;
    private StringEditText textName;
    private CurrencyEditText txtPrice;
    private CurrencyEditText txtCost;
    protected SwitchMaterial swEnable;

    private DocumentLookupInputView lkMaker;

    private DocumentLookupInputView lkType;

    private DocumentLookupInputView lkCondition;

    private DocumentLookupInputView lkModel;

    private StringEditText txtChassisNumber;
    private StringEditText txtEnginNumber;
    private StringEditText txtHorsepower;
    private IntegerEditText txtYear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.vehicle_form_fragment, container, false);

        textName = view.findViewById(R.id.txt_name);
        lkCategory = view.findViewById(R.id.txt_category);
        lkAccount = view.findViewById(R.id.txt_account);
        txtPrice = view.findViewById(R.id.txt_price);
        txtCost = view.findViewById(R.id.txt_cost);
        lkCategory.setValueChangedListner(this);
        lkAccount.setValueChangedListner(this);
        textName.setValueChangedListner(this);
        txtCost.setValueChangedListner(this);
        txtPrice.setValueChangedListner(this);
        swEnable = (SwitchMaterial) view.findViewById(R.id.sw_enable);
        swEnable.setSelected(true);
        lkMaker = view.findViewById(R.id.txt_maker);
        lkMaker.setValueChangedListner(this);
        lkType = view.findViewById(R.id.txt_type);
        lkType.setValueChangedListner(this);
        lkCondition = view.findViewById(R.id.txt_condition);
        lkCondition.setValueChangedListner(this);
        lkColor = view.findViewById(R.id.txt_color);
        lkColor.setValueChangedListner(this);
        lkModel = view.findViewById(R.id.txt_model);
        lkModel.setValueChangedListner(this);

        lkCategory.setActivity((AppCompatActivity) getActivity());
        lkColor.setActivity((AppCompatActivity) getActivity());
        lkAccount.setActivity((AppCompatActivity) getActivity());
        lkMaker.setActivity((AppCompatActivity) getActivity());
        lkType.setActivity((AppCompatActivity) getActivity());
        lkCondition.setActivity((AppCompatActivity) getActivity());
        lkModel.setActivity((AppCompatActivity) getActivity());

        txtChassisNumber = view.findViewById(R.id.txt_chassisNumber);
        txtChassisNumber.setValueChangedListner(this);
        txtEnginNumber = view.findViewById(R.id.txt_machinenumber);
        txtEnginNumber.setValueChangedListner(this);
        txtHorsepower = view.findViewById(R.id.txt_horsepower);
        txtHorsepower.setValueChangedListner(this);
        txtYear = view.findViewById(R.id.txt_production_year);
        txtYear.setValueChangedListner(this);

        return  view;
    }

    @Override
    public void readInputData(Vehicle item) {
        item.setName(textName.getValue());
        item.setCategory(lkCategory.getValue().getId());

        item.setAccount(lkAccount.getValue().getId());
        item.setPrice(txtPrice.getValue());
        item.setCost(txtCost.getValue());
        item.setDescription("");

        item.setMaker(lkMaker.getValue().getId());
        item.setType(lkType.getValue().getId());
        item.setCondition(lkCondition.getValue().getId());
        item.setModel(lkModel.getValue().getId());
        item.setColor(lkColor.getValue().getId());
        item.setChassisNo(txtChassisNumber.getValue());
        item.setEngineNo(txtEnginNumber.getValue());
        item.setYear(txtYear.getValue());
        item.setHorsepower(txtHorsepower.getValue());
        item.setEnable(swEnable.isChecked());
    }

    @Override
    public void assignValueToForm(Vehicle document) {
        textName.setValue(document.getName());
        lkCategory.setvalueId(document.getCategory());

        lkAccount.setvalueId(document.getAccount());
        txtPrice.setValue(document.getPrice());
        txtCost.setValue(document.getCost());


        lkMaker.setvalueId(document.getMaker());
        lkType.setvalueId(document.getType());
        lkCondition.setvalueId(document.getCondition());
        lkModel.setvalueId(document.getModel());
        lkColor.setvalueId(document.getColor());
        txtChassisNumber.setValue(document.getChassisNo());
        txtEnginNumber.setValue(document.getEngineNo());
        txtYear.setValue(document.getYear());
        txtHorsepower.setValue(document.getHorsepower());
        swEnable.setSelected(document.enable);

    }

    @Override
    public void resetFields() {
    }

    @Override
    public boolean validation() {
        isValid = textName.validate() && lkAccount.validate() && lkCategory.validate() && txtPrice.validate() && txtCost.validate()
                   && lkMaker.validate() && lkType.validate() && lkCondition.validate() && lkColor.validate() && lkModel.validate() && txtChassisNumber.validate() && txtEnginNumber.validate() && txtYear.validate() ;
        return isValid;
    }

}

