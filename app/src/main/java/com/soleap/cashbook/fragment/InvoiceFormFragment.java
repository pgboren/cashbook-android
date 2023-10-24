package com.soleap.cashbook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.BottomSheetMenuEditText;
import com.soleap.cashbook.common.widget.CurrencyEditText;
import com.soleap.cashbook.common.widget.DatePickerView;
import com.soleap.cashbook.common.widget.bottomsheetmenu.Menu;
import com.soleap.cashbook.common.widget.doclookup.DocumentLookupInputView;
import com.soleap.cashbook.document.Invoice;

public class InvoiceFormFragment extends DocFormFragment<Invoice> {

    private DocumentLookupInputView lkCustomer;
    private DocumentLookupInputView lkInstitute;
    private DocumentLookupInputView lkVehicle;
    private BottomSheetMenuEditText txtPaymentoption;
    private CurrencyEditText txtVehiclePrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.form_invoice_new_fragment, container, false);

        View input_invoice_no = view.findViewById(R.id.input_invoice_no);
        View input_invoice_date = view.findViewById(R.id.input_invoice_date);


        input_invoice_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return  view;
    }

    @Override
    public void readInputData(Invoice invoice) {
//        DatePickerView datePickerView = view.findViewById(R.id.dtDate);
//        invoice.setDate(datePickerView.getValue().getTimeInMillis());
//        invoice.setCustomer(lkCustomer.getValue().getId());
//        invoice.setInstitute(lkInstitute.getValue().getId());
//        invoice.setVehicle(lkVehicle.getValue().getId());
//        invoice.setPaymentoption(txtPaymentoption.getValue());
//        invoice.setPrice(txtVehiclePrice.getValue().floatValue());
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
    }
}
