package com.soleap.cashbook.common.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.BottomSheetMenuEditText;
import com.soleap.cashbook.common.widget.CurrencyEditText;
import com.soleap.cashbook.common.widget.DatePickerView;
import com.soleap.cashbook.common.widget.IntegerEditText;
import com.soleap.cashbook.common.widget.StringEditText;
import com.soleap.cashbook.common.widget.bottomsheetmenu.Menu;
import com.soleap.cashbook.common.widget.lookup.DocumentLookupEditText;
import com.soleap.cashbook.document.Invoice;

public class InvoiceFormFragment extends DocFormFragment<Invoice> {

    private DocumentLookupEditText lkCustomer;
    private DocumentLookupEditText lkInstitute;
    private DocumentLookupEditText lkItem;
    private DocumentLookupEditText lkColor;
    private StringEditText txtChassisNumber;
    private StringEditText txtMachineNumber;
    private IntegerEditText txtYear;
    private StringEditText txtPlateNumber;
    private BottomSheetMenuEditText txtVehicleCondition;

    private BottomSheetMenuEditText txtPaymentoption;
    private CurrencyEditText txtVehiclePrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.form_invoice_fragment, container, false);
        lkCustomer = view.findViewById(R.id.lk_contact);
        lkCustomer.setActivity((AppCompatActivity) getActivity());
        lkInstitute = view.findViewById(R.id.lk_institute);
        lkInstitute.setActivity((AppCompatActivity) getActivity());
        lkItem = view.findViewById(R.id.lk_item);
        lkItem.setActivity((AppCompatActivity) getActivity());
        lkColor = view.findViewById(R.id.lk_color);
        lkColor.setActivity((AppCompatActivity) getActivity());
        txtChassisNumber = view.findViewById(R.id.txt_chassisNumber);
        txtMachineNumber = view.findViewById(R.id.txt_machineNumber);
        txtYear = view.findViewById(R.id.txt_year);
        txtPlateNumber = view.findViewById(R.id.txt_plate_number);
        txtVehicleCondition = view.findViewById(R.id.txt_vehicle_condition);
        txtPaymentoption = view.findViewById(R.id.txt_paymentoption);

        Menu conditionMenu = new Menu();
        conditionMenu.setTitle("Condition");
        conditionMenu.addItem("NEW", ResourceUtil.getStringResourceByName(getContext(), "NEW"));
        conditionMenu.addItem("SECOND_HAND", ResourceUtil.getStringResourceByName(getContext(), "SECONDHAND"));
        txtVehicleCondition.setMenu(conditionMenu);

        Menu paymentoptionMenu = new Menu();
        paymentoptionMenu.setTitle("PAYMENT_OPTION");
        paymentoptionMenu.addItem("FULL_PAYMENT", ResourceUtil.getStringResourceByName(getContext(), "FULL_PAYMENT"));
        paymentoptionMenu.addItem("INSTALLMENT", ResourceUtil.getStringResourceByName(getContext(), "INSTALLMENT"));
        txtPaymentoption.setMenu(paymentoptionMenu);

        txtVehiclePrice = view.findViewById(R.id.txt_price);

        lkCustomer.setValueChangedListner(this);
        lkInstitute.setValueChangedListner(this);
        lkItem.setValueChangedListner(this);
        lkColor.setValueChangedListner(this);
        txtChassisNumber.setValueChangedListner(this);
        txtMachineNumber.setValueChangedListner(this);
        txtYear.setValueChangedListner(this);

        txtPlateNumber.setValueChangedListner(this);
        txtVehicleCondition.setValueChangedListner(this);
        txtVehiclePrice.setValueChangedListner(this);

        return  view;
    }

    @Override
    public void readInputData(Invoice invoice) {
        DatePickerView datePickerView = view.findViewById(R.id.dtDate);
        invoice.setDate(datePickerView.getValue().getTimeInMillis());
        invoice.setCustomer(lkCustomer.getValue().getId());
        invoice.setInstitute(lkInstitute.getValue().getId());
        invoice.setItem(lkItem.getValue().getId());
        invoice.setColor(lkColor.getValue().getId());
        invoice.setChassisNumber(txtChassisNumber.getValue());
        invoice.setMachineNumber(txtMachineNumber.getValue());
        invoice.setYear(txtYear.getValue().intValue());
        invoice.setPlateNumber(txtPlateNumber.getValue());
        invoice.setCondition(txtVehicleCondition.getValue());
        invoice.setPrice(txtVehiclePrice.getValue().floatValue());
    }

    @Override
    public void assignValueToForm(Invoice document) {
    }

    @Override
    public void resetFields() {
    }

    @Override
    public boolean validation() {
        isValid = true;
        return isValid;
    }

    @Override
    public void onChanged(Object value, int viewId) {
        super.onChanged(value, viewId);

        if (viewId == R.id.lk_item) {
            double price = ((Double)lkItem.getValue().getDataValue("price").getValue()).doubleValue();
            txtVehiclePrice.setValue(price);
        }

    }
}
