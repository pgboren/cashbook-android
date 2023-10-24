package com.soleap.cashbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.viewholder.OnRecyclerViewListner;
import com.soleap.cashbook.common.widget.doclookup.DocumentListBottomSheetFragment;
import com.soleap.cashbook.common.widget.doclookup.DocumentListBottomSheetFragmentEventListner;
import com.soleap.cashbook.common.widget.doclookup.DocumentLookupInputView;
import com.soleap.cashbook.common.widget.input.BaseButtomSheetInputView;
import com.soleap.cashbook.common.widget.lookup.BaseLookupRecyclerViewAdapter;
import com.soleap.cashbook.common.widget.lookup.TextEnumRecyclerViewAdapter;
import com.soleap.cashbook.view.DocumentInfo;

import java.util.Calendar;

public class DocLookupInputView extends BaseButtomSheetInputView<Document> {
    private DocumentInfo documentInfo;

    private String fieldname;

    private FragmentManager fragmentManager;

    public DocLookupInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        fieldname = a.getString(R.styleable.BaseButtomSheetInputView_div_doc_field_name);
        String docName = a.getString(R.styleable.BaseButtomSheetInputView_div_doc_name);
        this.documentInfo = DocumentInfo.getDocumentInfo(docName);
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        fragmentManager = fragmentActivity.getSupportFragmentManager();
    }

    @Override
    protected void onClick() {
        lookupDocument();
    }

    private void lookupDocument() {
        com.soleap.cashbook.common.widget.doclookup.DocumentListBottomSheetFragment demoBottomsheet = new DocumentListBottomSheetFragment(true, "Expanded state");
        demoBottomsheet.setDocumentInfo(this.documentInfo);
        demoBottomsheet.setEventListner(new DocumentListBottomSheetFragmentEventListner() {
            @Override
            public void onItemSelected(Document documentSnapshot) {
                setValue((DocumentSnapshot) documentSnapshot);
                if (valueChangedListner != null) {
//                    valueChangedListner.onChanged(documentSnapshot, DocumentLookupInputView.this.getId());
                }
            }
        });
        demoBottomsheet.show(fragmentManager, "Expanded");
    }

    @Override
    protected void onValueSet() {
    }

    @Override
    protected void updateDisplayValue() {
        DocumentSnapshot doc = (DocumentSnapshot) getValue();
        txtValue.setText(doc.getDataValue(fieldname).getValue().toString());
    }

}
