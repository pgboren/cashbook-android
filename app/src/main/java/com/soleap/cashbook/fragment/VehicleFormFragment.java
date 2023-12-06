package com.soleap.cashbook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.common.widget.CurrencyEditText;
import com.soleap.cashbook.common.widget.IntegerEditText;
import com.soleap.cashbook.common.widget.StringEditText;
import com.soleap.cashbook.document.Vehicle;
import com.soleap.cashbook.widget.refdoc.RefDocLookupInputView;

public class VehicleFormFragment extends DocFormFragment<Vehicle> {
    private RefDocLookupInputView lkCategory;
    private RefDocLookupInputView lkColor;
    private StringEditText textName;
    protected SwitchMaterial swEnable;
    private RefDocLookupInputView lkMaker;
    private RefDocLookupInputView lkType;
    private RefDocLookupInputView lkCondition;
    private RefDocLookupInputView lkModel;
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
        lkCategory.setValueChangedListner(this);
        textName.setValueChangedListner(this);
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
        item.setCategory(lkCategory.getValue().get_id());
        item.setDescription("");
        item.setMaker(lkMaker.getValue().get_id());
        item.setType(lkType.getValue().get_id());
        item.setCondition(lkCondition.getValue().get_id());
        item.setModel(lkModel.getValue().get_id());
        item.setColor(lkColor.getValue().get_id());
        item.setChassisNo(txtChassisNumber.getValue());
        item.setEngineNo(txtEnginNumber.getValue());
        item.setYear(txtYear.getValue());
        item.setHorsepower(txtHorsepower.getValue());
        item.setEnable(swEnable.isChecked());
    }

    @Override
    public void assignValueToForm(Vehicle document) {
        textName.setValue(document.getName());
//        lkMaker.setvalueId(document.getMaker());
//        lkType.setvalueId(document.getType());
//        lkCondition.setvalueId(document.getCondition());
//        lkModel.setvalueId(document.getModel());
//        lkColor.setvalueId(document.getColor());
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
        isValid = textName.validate() && lkMaker.validate() && lkType.validate() && lkCondition.validate() && lkColor.validate() && lkModel.validate() && txtChassisNumber.validate() && txtEnginNumber.validate() && txtYear.validate() ;
        return isValid;
    }

}

