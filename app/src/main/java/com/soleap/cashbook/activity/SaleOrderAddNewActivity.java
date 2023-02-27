package com.soleap.cashbook.activity;

import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.CouponNumberInputView;
import com.soleap.cashbook.common.widget.DatePickerView;
import com.soleap.cashbook.common.widget.OnValueChanged;
import com.soleap.cashbook.common.widget.lookup.DocLookupTextInputView;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.document.SaleOrder;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;

import java.util.Calendar;

public class SaleOrderAddNewActivity extends DocAddNewActivity implements OnValueChanged {

    public final static int SELECT_BRANCH_LOOKUP_REQUEST_CODE = 201;

    final Calendar calendar = Calendar.getInstance();
    private DocumentSnapshot branch;

    public static int RESULT_GENERRAL = 2001;
    private TextView txtDate;
    public String label = "Customer";
    private String paymentOption;
    private String vehicleCondition;

    protected BottomSheetMenu buttomActionsMenu;
    private CouponNumberInputView couponNumberInputView;
    private DatePickerView datePickerInputView;
    private DocLookupTextInputView branchInputView;
    private DocLookupTextInputView customerInputView;
    private DocLookupTextInputView instituteInputView;
    private DocLookupTextInputView itemInputView;
    private TextInputEditText txtQuantity;
    private TextInputEditText txtPrice;
    private TextInputEditText txtBookingAmt;
    private TextInputEditText txtPaymentOptions;
    private TextInputEditText txtVehicleCondition;


    protected String getViewTitle() {
        return "nav_menu_installment_payment_sale_request";
    }

    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_sale_order);
        initInputView();
        setTitle(getString(R.string.nav_menu_installment_payment_sale_request));
    }

    @Override
    protected void initInputView() {
        datePickerInputView = findViewById(R.id.so_datepicker);
        datePickerInputView.setOnValueChangedListner(this);
        branchInputView = findViewById(R.id.so_branch);
        branchInputView.setOnValueChangedListner(this);
        customerInputView = findViewById(R.id.so_customer);
        customerInputView.setOnValueChangedListner(this);
        instituteInputView = findViewById(R.id.so_institute);
        instituteInputView.setOnValueChangedListner(this);
        itemInputView = findViewById(R.id.so_item);
        itemInputView.setOnValueChangedListner(this);
        txtQuantity = findViewById(R.id.so_quantity);
        txtQuantity.addTextChangedListener(this);
        txtPrice = findViewById(R.id.so_price);
        txtPrice.addTextChangedListener(this);
        txtPaymentOptions = findViewById(R.id.so_paymentOptions);
        txtPaymentOptions.addTextChangedListener(this);
        txtVehicleCondition = findViewById(R.id.so_vehicle_condition);
        txtVehicleCondition.addTextChangedListener(this);
        txtBookingAmt = findViewById(R.id.so_booking_amt);
        txtBookingAmt.addTextChangedListener(this);

        txtPaymentOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptionsMenu(R.menu.payment_options_menu);
            }
        });

        txtVehicleCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptionsMenu(R.menu.vehicle_condition_menu);
            }
        });


        itemInputView.setOnValueChangedListner(new OnValueChanged() {
            @Override
            public void onChanged(Object value) {
                DocumentSnapshot doc = (DocumentSnapshot) value;
                ViewData data = doc.getDataValue("root").getDataValue("general");
                double price = (Double) data.getDataValue("price").getValue();
                txtPrice.setText(String.valueOf(price));
            }
        });

    }

    @Override
    protected void readInputData(Document document) {
        SaleOrder saleOrder = (SaleOrder) document;
        saleOrder.setDate(0);
        saleOrder.setBranch(branchInputView.getValue().getId());
        saleOrder.setCustomer(customerInputView.getValue().getId());
        saleOrder.setItem(itemInputView.getValue().getId());
        saleOrder.setPaymentOption(paymentOption);
        saleOrder.setVehicleCondition(vehicleCondition);
        saleOrder.setQuantity(1);
        Log.i("CashBook _ Test", txtPrice.getText().toString());
        saleOrder.setPrice(Double.valueOf(txtPrice.getText().toString()));
        saleOrder.setBookingAmount(Double.valueOf(txtBookingAmt.getText().toString()));
        saleOrder.setStatus(SaleOrder.ORDER_STATUS_NEW);
    }

    @Override
    protected boolean validation() {
        isValid = true;

        if (datePickerInputView.getValue() == null) {
            isValid = false;
        } else {
            isValid = isValid && true;
        }

        if (branchInputView.getValue() == null) {
            isValid = false;
        } else {
            isValid = isValid && true;
        }

        if (customerInputView.getValue() == null) {
            isValid = false;
        } else {
            isValid = isValid && true;
        }

        if (itemInputView.getValue() == null) {
            isValid = false;
        } else {
            isValid = isValid && true;
        }

        if (txtPrice.getText().toString().isEmpty()) {
            isValid = false;
        } else {
            isValid = isValid && true;
        }

        if (txtPaymentOptions.getText().toString().isEmpty()) {
            isValid = false;
        } else {
            isValid = isValid && true;
        }

        if (txtVehicleCondition.getText().toString().isEmpty()) {
            isValid = false;
        } else {
            isValid = isValid && true;
        }

        if (txtBookingAmt.getText().toString().isEmpty()) {
            txtBookingAmt.setText("0");
        } else {
            isValid = isValid && true;
        }

        super.validation();
        return isValid;
    }

    protected void showOptionsMenu(int menuId) {
//        buttomActionsMenu = new BottomSheetMenu.Builder(this, this, "", menuId, R.layout.text_menu_item).create();
        buttomActionsMenu.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_payment_option_loan) {
            paymentOption = SaleOrder.ORDER_PAYMENT_TYPE_LOAN;
            txtPaymentOptions.setText(ResourceUtil.getStringResourceByName(this, paymentOption));
        }

        if (id == R.id.menu_item_payment_option_full_pay) {
            paymentOption = SaleOrder.ORDER_PAYMENT_TYPE_FULL_PAY;
            txtPaymentOptions.setText(ResourceUtil.getStringResourceByName(this, paymentOption));
        }

        if (id == R.id.menu_item_vehicle_condition_new) {
            vehicleCondition = SaleOrder.ORDER_PAYMENT_VEHICLE_STATUS_NEW;
            txtVehicleCondition.setText(ResourceUtil.getStringResourceByName(this, vehicleCondition));
        }

        if (id == R.id.menu_item_vehicle_condition_used) {
            vehicleCondition = SaleOrder.ORDER_PAYMENT_VEHICLE_STATUS_USED;
            txtVehicleCondition.setText(ResourceUtil.getStringResourceByName(this, vehicleCondition));
        }

        if (id == R.id.menu_item_vehicle_condition_secondhand) {
            vehicleCondition = SaleOrder.ORDER_PAYMENT_VEHICLE_STATUS_SECONDHAND;
            txtVehicleCondition.setText(ResourceUtil.getStringResourceByName(this, vehicleCondition));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        super.afterTextChanged(editable);


    }

    @Override
    public void onChanged(Object value) {
        this.validation();
    }
}