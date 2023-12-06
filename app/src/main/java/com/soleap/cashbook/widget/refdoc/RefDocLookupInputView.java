package com.soleap.cashbook.widget.refdoc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.RefDocument;
import com.soleap.cashbook.common.widget.BaseTextInputView;
import com.soleap.cashbook.common.widget.doclookup.DocumentListBottomSheetFragmentEventListner;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.view.DocumentInfo;

public class RefDocLookupInputView extends BaseTextInputView<Document> {

    private static String TAG = "DocumentLookupEditText";
    private String docName;
    private String fieldname;

    protected APIInterface apiInterface;
    private AppCompatActivity activity;
    private DocumentInfo documentInfo;

    private FragmentManager fragmentManager;

    public RefDocLookupInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        editText.setTextIsSelectable(false);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lookupDocument();
            }
        });
    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        fieldname = a.getString(R.styleable.EditTextInputView_ev_doc_field_name);
        docName = a.getString(R.styleable.EditTextInputView_ev_doc_name);
        this.documentInfo = DocumentInfo.getDocumentInfo(docName);
        Drawable endIconDrawable = a.getDrawable(R.styleable.EditTextInputView_ev_endIconDrawable);
        if (endIconDrawable != null) {
            textInputLayout.setEndIconDrawable(endIconDrawable);
        }
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        fragmentManager = fragmentActivity.getSupportFragmentManager();
    }

    private void lookupDocument() {
        RefDocBottomSheetFragment demoBottomsheet = new RefDocBottomSheetFragment(documentInfo, listner);
        demoBottomsheet.show(fragmentManager, "Expanded");
    }

    private RefDocBottomSheetFragment.EventListner listner = new RefDocBottomSheetFragment.EventListner() {
        @Override
        public void onItemClick(RefDocument refDoc, int position) {
            setValue(refDoc);
        }
    };

    @Override
    protected void onValueSet(Document value) {
        TextInputEditText editText = findViewById(R.id.editText);
        editText.removeTextChangedListener(textWatcher);
        RefDocument refDocument = (RefDocument) value;
        editText.setText( value == null ? "" : refDocument.getName());
        editText.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onTextChange(String text) {
        validate();
    }

    @Override
    public boolean validate() {
        if (!errorEnabled) {
            return true;
        }
        return getValue() != null ? (getValue().toString().equals("")  ? false : true) : false;
    }
}
