 package com.soleap.cashbook.activity;

 import android.text.Editable;
 import android.text.TextWatcher;

 import com.google.android.material.switchmaterial.SwitchMaterial;
 import com.google.android.material.textfield.TextInputEditText;
 import com.google.android.material.textfield.TextInputLayout;
 import com.soleap.cashbook.R;
 import com.soleap.cashbook.common.activity.EditRestApiActivity;
 import com.soleap.cashbook.document.Category;
 import com.soleap.cashbook.document.Color;
 import com.soleap.cashbook.document.DocumentInfo;
 import com.soleap.cashbook.document.Item;

 import org.json.JSONException;
 import org.json.JSONObject;

 public class BsItemDocEditActivity extends EditRestApiActivity<Item> implements TextWatcher {

     protected TextInputEditText txtName;
     private TextInputLayout inputLayoutName;
     private SwitchMaterial swEnable;

     @Override
     protected String getViewTitle() {
         return "add_" + documentName;
     }

     @Override
     protected void setViewContent() {
         this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
         setContentView(R.layout.activity_form_item);
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
             inputLayoutName.setError(BsItemDocEditActivity.this.getString(R.string.duplicate_category_name));
         }
     }

     @Override
     protected void assignValueToForm(Item model) {
         Item item = (Item) model;
         TextInputEditText textName = findViewById(R.id.txt_customer_name);
         TextInputEditText textNameKh = findViewById(R.id.txt_name_kh);
         TextInputEditText textCategory = findViewById(R.id.txt_category);
         TextInputEditText textColor = findViewById(R.id.txt_color);
         TextInputEditText textPrice = findViewById(R.id.txt_price);
         TextInputEditText textInstallmentPaymentPrice = findViewById(R.id.txt_installmentpaymentprice);
         TextInputEditText textCost = findViewById(R.id.txt_cost);
         TextInputEditText textDescription = findViewById(R.id.txt_desc);

         txtName.setText(item.getName());
         textNameKh.setText(item.getNameKh());
         textCategory.setText(item.getCategory().getName());
         textCategory.setTag(item.getCategory().getId());
         textColor.setText(item.getColor().getName());
         textColor.setTag(item.getColor().getId());
         textPrice.setText(String.valueOf(item.getPrice()));
         textInstallmentPaymentPrice.setText(String.valueOf(item.getInstallmentPaymentPrice()));
         textCost.setText(String.valueOf(item.getCost()));
         textDescription.setText(item.getDescription());
         swEnable.setChecked(item.isEnable());
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
     protected void readInputData(Item document) {
         Item item = (Item)document;
         TextInputEditText textCategory = findViewById(R.id.txt_category);
         TextInputEditText textColor = findViewById(R.id.txt_color);
         item.setNameKh(((TextInputEditText)findViewById(R.id.txt_name_kh)).getText().toString());
         Category category = new Category();
         category.setId(textCategory.getTag().toString());
         item.setCategory(category);
         Color color = new Color();
         color.setId(textColor.getTag().toString());
         item.setPrice(Double.parseDouble(((TextInputEditText)findViewById(R.id.txt_price)).getText().toString()));
         item.setInstallmentPaymentPrice(Double.parseDouble(((TextInputEditText)findViewById(R.id.txt_installmentpaymentprice)).getText().toString()));
         item.setCost(Double.parseDouble(((TextInputEditText)findViewById(R.id.txt_cost)).getText().toString()));
         item.setColor(color);
         item.setDescription(((TextInputEditText)findViewById(R.id.txt_name_kh)).getText().toString());
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