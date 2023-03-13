package com.soleap.cashbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.DocItemsViewActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.widget.OnValueChanged;
import com.soleap.cashbook.common.widget.lookup.DocLookupTextInputView;
import com.soleap.cashbook.document.SaleOrderItem;

public class SaleOrderitemInputActivity extends DocItemsViewActivity implements TextWatcher, OnValueChanged {

    private DocLookupTextInputView dlItem;
    private TextInputEditText txtPrice;
    private TextInputEditText txtQty;
    private MaterialButton btnAdd;

    @Override
    protected void setViewContent() {
        setContentView(R.layout.activity_form_sale_order_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            setSupportActionBar(toolbar);
        }
        setTitle(getString(R.string.add_sale_order_item));
        dlItem = findViewById(R.id.dl_item);
        txtPrice = findViewById(R.id.txt_price);
        txtQty = findViewById(R.id.txt_qty);
        txtQty.setFocusable(false);

        txtQty.addTextChangedListener(this);
        txtPrice.addTextChangedListener(this);

//        dlItem.setOnValueChangedListner(new OnValueChanged() {
//            @Override
//            public void onChanged(Object value) {
//                ViewData data = ((DocumentSnapshot)value).getDataValue("root").getDataValue("general");
//                double price = (Double) data.getDataValue("price").getValue();
//                 txtPrice.setText(String.valueOf(price));
//            }
//        });

        btnAdd = findViewById(R.id.btn_add_more);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            SaleOrderItem item = new SaleOrderItem();
            ViewData data = ((DocumentSnapshot)dlItem.getValue()).getDataValue("root").getDataValue("general");
            item.setItemId(dlItem.getValue().getId());
            item.setItemName(data.getDataValue("name").getValue().toString());


            if (data.getDataValue("color").getValue() != null) {
                item.setItemColor(data.getDataValue("color").getValue().toString());
            }

            item.setItemPower(data.getDataValue("power").getValue().toString());
            item.setItemYear(data.getDataValue("year").getValue().toString());
            item.setItemStatus(getApplicationContext().getString(R.string.NEW));
            item.setPrice(Double.parseDouble(txtPrice.getText().toString()));
            item.setQuantity(Double.parseDouble(txtQty.getText().toString()));
            Intent returnIntent = new Intent();
            returnIntent.putExtra(ITEM_DOC, item);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
            }
        });
    }

    protected void validation() {
        boolean isValid = false;

        if (dlItem.getValue() != null) {
            isValid = true;
        }
        else {
            isValid = false;
        }
        if (!txtQty.getText().toString().isEmpty()) {
            isValid = isValid && true;
        }
        else {
            isValid = false;
        }

        if (!txtPrice.getText().toString().isEmpty()) {
            isValid = isValid && true;
        }
        else {
            isValid = false;
        }

        btnAdd.setEnabled(isValid);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
         validation();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onChanged(Object value) {
        validation();
    }

}
