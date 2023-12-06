package com.soleap.cashbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.util.MedialUtils;
import com.soleap.cashbook.common.viewholder.OnRecyclerViewListner;
import com.soleap.cashbook.common.widget.doclookup.DocumentListBottomSheetFragment;
import com.soleap.cashbook.common.widget.doclookup.DocumentListBottomSheetFragmentEventListner;
import com.soleap.cashbook.common.widget.doclookup.DocumentLookupInputView;
import com.soleap.cashbook.common.widget.input.BaseButtomSheetInputView;
import com.soleap.cashbook.common.widget.lookup.BaseLookupRecyclerViewAdapter;
import com.soleap.cashbook.common.widget.lookup.TextEnumRecyclerViewAdapter;
import com.soleap.cashbook.document.Institute;
import com.soleap.cashbook.view.DocumentInfo;

import java.lang.reflect.Field;
import java.util.Calendar;

public class DocLookupInputView extends BaseButtomSheetInputView<Document> {
    private DocumentInfo documentInfo;

    protected String fieldname;

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
        DocumentListBottomSheetFragment demoBottomsheet = new DocumentListBottomSheetFragment(true, "Expanded state");
        demoBottomsheet.setDocumentInfo(this.documentInfo);
        demoBottomsheet.setEventListner(new DocumentListBottomSheetFragmentEventListner() {
            @Override
            public void onItemSelected(Document documentSnapshot) {
                setValue((Document) documentSnapshot);
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
        try {
            Document doc = (Document) getValue();
            Field privateField = doc.getClass().getDeclaredField("name");
            privateField.setAccessible(true);
            txtValue.setText(privateField.get(doc).toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
