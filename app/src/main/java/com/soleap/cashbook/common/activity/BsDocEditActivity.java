 package com.soleap.cashbook.common.activity;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.common.document.BsDocument;


import org.json.JSONException;
import org.json.JSONObject;

 public class BsDocEditActivity extends EditRestApiActivity<BsDocument> implements TextWatcher {

     protected TextInputEditText txtName;
     private TextInputLayout inputLayoutName;
     private SwitchMaterial swEnable;

     @Override
     protected void setViewContent() {
         this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
         setContentView(R.layout.activity_form_bsdoc);
         initInputView();
     }

     protected void initInputView() {
         txtName = findViewById(R.id.txt_customer_name);
         inputLayoutName = (TextInputLayout)findViewById(R.id.inputLayout_customer_name);
         txtName.addTextChangedListener(this);
         swEnable = (SwitchMaterial) findViewById(R.id.sw_enable);
     }

     @Override
     protected void onError(JSONObject errorObject) throws JSONException {
         if (errorObject.getString("code").equals("11000")) {
             inputLayoutName.setErrorEnabled(true);
             inputLayoutName.setError(BsDocEditActivity.this.getString(R.string.duplicate_category_name));
         }
     }

     @Override
     protected void resetFields() {
         txtName.setText("");
         swEnable.setSelected(true);
     }

     @Override
     protected boolean validation() {
         if (txtName.getText().toString().isEmpty()) {
             inputLayoutName.setHelperText(getString(R.string.require));
             isValid = false;
         }
         else {
             inputLayoutName.setHelperText("");
             isValid = true;
         }
         return super.validation();
     }

     @Override
     protected void readInputData(BsDocument document) {
         document.setName(txtName.getText().toString());
         document.setEnable(swEnable.isChecked());
     }

     @Override
     protected void assignValueToForm(BsDocument document) {
         TextInputEditText txtName = (TextInputEditText)this.findViewById(R.id.txt_customer_name);
         txtName.setText(document.getName());
         swEnable.setChecked(document.isEnable());
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

 }