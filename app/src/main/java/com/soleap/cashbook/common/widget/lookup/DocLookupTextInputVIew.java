package com.soleap.cashbook.common.widget.lookup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocLookUpActivity;
import com.soleap.cashbook.document.DocumentInfo;

public class DocLookupTextInputVIew extends LinearLayout {

    private String value;
    private String docName;

    private TextInputLayout textInputLayout;
    private TextInputEditText editText;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    public DocLookupTextInputVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.DocLookupTextInputVIew, 0, 0);
        String title = a.getString(R.styleable.DocLookupTextInputVIew_dltv_title);
        docName = a.getString(R.styleable.DocLookupTextInputVIew_dltv_doc_name);
        String helperText = a.getString(R.styleable.DocLookupTextInputVIew_dltv_helperText);
        boolean errorEnabled = a.getBoolean(R.styleable.DocLookupTextInputVIew_dltv_errorEnabled, false);
        Drawable endIconDrawable = a.getDrawable(R.styleable.DocLookupTextInputVIew_dltv_endIconDrawable);

        setOrientation(VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.doclookuptextinputview, this, true);
        textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout.setHint(title);
        textInputLayout.setErrorEnabled(errorEnabled);
        textInputLayout.setHelperText(helperText);
        textInputLayout.setEndIconDrawable(endIconDrawable);
        editText = findViewById(R.id.editText);
        editText.setTextIsSelectable(false);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lookupDocument(DocumentInfo.ITEM, BsDocLookUpActivity.LOOK_UP_CATEGORY_REQUEST_CODE);
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BsDocLookUpActivity.class);
                intent.putExtra(DocumentInfo.DOCUMENT_NAME, docName);
                intent.putExtra(BsDocLookUpActivity.SHOW_PHOTO, true);
                activityResultLauncher.launch(intent);
            }
        });

        AppCompatActivity activity = (AppCompatActivity) context;
        activityResultLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            editText.setText(data.getStringExtra(BsDocLookUpActivity.DOC_TEXT));
                        }


                    }
                }
        );
    }


    private void lookupDocument(String documentName,  int requestCode) {

    }
}
