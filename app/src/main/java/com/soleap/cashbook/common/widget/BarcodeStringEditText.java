package com.soleap.cashbook.common.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;

public class BarcodeStringEditText extends BaseTextInputView<String> {
    private ActivityResultLauncher<Intent> activityResultLauncher;

    public BarcodeStringEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        AppCompatActivity activity = (AppCompatActivity) context;
        activityResultLauncher = activity.registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        String data = result.getData().getStringExtra("SCAN_RESULT");
                        TextInputEditText editText = findViewById(R.id.editText);
                        editText.setText(data);
                    }
                }
            }
        );
    }

    @Override
    protected void onTextChange(String text) {
        value = text;
        if (valueChangedListner != null) {
            valueChangedListner.onChanged(value, BarcodeStringEditText.this.getId());
        }
    }

    @Override
    public boolean validate() {
        if (!errorEnabled) {
            return true;
        }
        return getValue() != null ? (getValue().toString().equals("")  ? false : true) : false;
    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        TextInputEditText editText = findViewById(R.id.editText);
        editText.setTextSize(15);
        if (editText.getLineCount() > 1) {
            editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        }
        textInputLayout.setEndIconOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
                activityResultLauncher.launch(intent);
            }
        });
    }
    @Override
    protected int getInputLayout() {
        return R.layout.input_barcode_edittext;
    }
}
