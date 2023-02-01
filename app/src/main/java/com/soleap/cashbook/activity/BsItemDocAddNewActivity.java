package com.soleap.cashbook.activity;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocAddNewActivity;
import com.soleap.cashbook.common.activity.BsDocLookUpActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.document.Color;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.document.Item;

public class BsItemDocAddNewActivity extends BsDocAddNewActivity {

    private static final String TAG = "BsItemDocAddNewActivity";
    private TextInputEditText textCategory;
    private TextInputEditText textColor;

    @Override
    protected void readInputData(Document document) {
        super.readInputData(document);
        Item item =  (Item)document;
        item.setNameKh(((TextInputEditText)findViewById(R.id.txt_name_kh)).getText().toString());
        Category category = new Category();
        category.setId(textCategory.getTag().toString());
        item.setCategory(category);
        Color color = new Color();
        color.setId(textColor.getTag().toString());
        item.setYear(((TextInputEditText)findViewById(R.id.txt_year)).getText().toString());
        item.setPower(((TextInputEditText)findViewById(R.id.txt_power)).getText().toString());
        item.setPrice(Double.parseDouble(((TextInputEditText)findViewById(R.id.txt_price)).getText().toString()));
        item.setInstallmentPaymentPrice(Double.parseDouble(((TextInputEditText)findViewById(R.id.txt_installmentpaymentprice)).getText().toString()));
        item.setCost(Double.parseDouble(((TextInputEditText)findViewById(R.id.txt_cost)).getText().toString()));
        item.setColor(color);
        item.setDescription(((TextInputEditText)findViewById(R.id.txt_name_kh)).getText().toString());
    }

    @Override
    protected void setViewContent() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        setContentView(R.layout.activity_form_item);
        initInputView();
        textCategory = findViewById(R.id.txt_category);
        textCategory.setTextIsSelectable(false);
        textCategory.setFocusable(false);
        textCategory.setFocusableInTouchMode(false);
        textCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lookupDocument(DocumentInfo.CATEGORY, BsDocLookUpActivity.LOOK_UP_CATEGORY_REQUEST_CODE);
            }
        });

        textColor = findViewById(R.id.txt_color);
        textColor.setTextIsSelectable(false);
        textColor.setFocusable(false);
        textColor.setFocusableInTouchMode(false);
        textColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lookupDocument(DocumentInfo.COLOR, BsDocLookUpActivity.LOOK_UP_COLOR_REQUEST_CODE);
            }
        });

    }

    private void lookupDocument(String documentName,  int requestCode) {
        Intent intent = new Intent(this, BsDocLookUpActivity.class);
        intent.putExtra(DocumentInfo.DOCUMENT_NAME, documentName);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BsDocLookUpActivity.LOOK_UP_CATEGORY_REQUEST_CODE) {
            DocumentSnapshot doc = (DocumentSnapshot) data.getSerializableExtra(BsDocLookUpActivity.LOOK_UP_DOCUMENT);
            String name = doc.getDataValue("category").getDataValue("name").getValue().toString();
            textCategory.setText(name);
            textCategory.setTag(doc.getId());
        }

        if (requestCode == BsDocLookUpActivity.LOOK_UP_COLOR_REQUEST_CODE) {
            DocumentSnapshot doc = (DocumentSnapshot) data.getSerializableExtra(BsDocLookUpActivity.LOOK_UP_DOCUMENT);
            String name = doc.getDataValue("color").getDataValue("name").getValue().toString();
            textColor.setText(name);
            textColor.setTag(doc.getId());
        }
    }
}
