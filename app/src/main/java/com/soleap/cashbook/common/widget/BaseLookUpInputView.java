package com.soleap.cashbook.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.widget.lookup.DocumentListBottomSheetFragment;
import com.soleap.cashbook.common.widget.lookup.DocumentListBottomSheetFragmentEventListner;
import com.soleap.cashbook.common.widget.lookup.DocumentLookupEditText;
import com.soleap.cashbook.view.DocumentInfo;

public abstract class BaseLookUpInputView<T> extends LinearLayout {

    protected boolean errorEnabled;

    protected T value;

    protected OnValueChangedListner valueChangedListner;
    protected View layoutPrompt;
    protected View layoutView;
    private DocumentInfo documentInfo;

    public abstract boolean validate();

    protected abstract int getInputLayout();

    protected abstract void renderView();

    public void setValue(T value) {
        this.value = value;
        onValueSet(value);
    }

    public T getValue() {
        return value;
    }

    protected void onValueSet(T value) {
        if (value != null) {
            layoutPrompt.setVisibility(GONE);
            layoutView.setVisibility(VISIBLE);
            renderView();
        }
        else {
            layoutPrompt.setVisibility(VISIBLE);
            layoutView.setVisibility(GONE);
        }
    }

    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        String hint = a.getString(R.styleable.BaseLookUpInputView_lookup_view_hint);
        errorEnabled = a.getBoolean(R.styleable.BaseLookUpInputView_lookup_view_errorEnabled, false);
        String helperText = a.getString(R.styleable.BaseLookUpInputView_lookup_view_helperText);
        String docName = a.getString(R.styleable.BaseLookUpInputView_lookup_view_doc_name);
        this.documentInfo = DocumentInfo.getDocumentInfo(docName);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(getInputLayout(), this, true);
        layoutPrompt = findViewById(R.id.layout_prompt);
        layoutView = findViewById(R.id.layout_view);
        ImageView btnLookup = findViewById(R.id.btn_lookup);
        btnLookup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                lookupDocument();
            }
        });

        ImageView btnClear = findViewById(R.id.btn_remove);
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setValue(null);
            }
        });

    }

    public BaseLookUpInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BaseLookUpInputView, 0, 0);
        processAttributeSet(context, attrs, a);
        a.recycle();
    }

    private void lookupDocument() {
        DocumentListBottomSheetFragment demoBottomsheet = new DocumentListBottomSheetFragment(true, "Expanded state");
        demoBottomsheet.setDocumentInfo(this.documentInfo);
        demoBottomsheet.setEventListner(new DocumentListBottomSheetFragmentEventListner() {
            @Override
            public void onItemSelected(Document documentSnapshot) {
                setValue((T) documentSnapshot);
            }
        });
        AppCompatActivity activity = (AppCompatActivity) getContext();
        demoBottomsheet.show(activity.getSupportFragmentManager(), "Expanded");
    }




}
