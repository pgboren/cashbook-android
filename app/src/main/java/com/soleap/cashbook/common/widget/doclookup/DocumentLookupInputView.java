package com.soleap.cashbook.common.widget.doclookup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewDocumentSnapshot;
import com.soleap.cashbook.common.widget.BaseTextInputView;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.view.DocumentInfo;

import java.lang.reflect.Field;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentLookupInputView extends BaseTextInputView<Document> {
    private static String TAG = "DocumentLookupEditText";
    private String docName;
    private String fieldname;

    protected APIInterface apiInterface;
    private AppCompatActivity activity;

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    private DocumentInfo documentInfo;

    @Override
    protected void onTextChange(String text) {
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
    }

    public DocumentLookupInputView(Context context, @Nullable AttributeSet attrs) {
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
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    private void lookupDocument() {
        DocumentListBottomSheetFragment demoBottomsheet = new DocumentListBottomSheetFragment(true, "Expanded state");
        demoBottomsheet.setDocumentInfo(this.documentInfo);
        demoBottomsheet.setEventListner(new DocumentListBottomSheetFragmentEventListner() {
            @Override
            public void onItemSelected(Document documentSnapshot) {
                setValue(documentSnapshot);
                if (valueChangedListner != null) {
                    valueChangedListner.onChanged(documentSnapshot, DocumentLookupInputView.this.getId());
                }
            }
        });
        demoBottomsheet.show(activity.getSupportFragmentManager(), "Expanded");
    }

    @Override
    protected void onValueSet(Document value) {
        try {
            TextInputEditText editText = findViewById(R.id.editText);
            editText.removeTextChangedListener(textWatcher);
            Field privateField = value.getClass().getField(fieldname);
            privateField.setAccessible(true);
            String text = privateField.get(value).toString();
            editText.setText(text);
            editText.addTextChangedListener(textWatcher);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validate() {
        if (!errorEnabled) {
            return true;
        }
        return getValue() != null ?  true : false;
    }

    public void setvalueId(String id) {
        Call<ViewDocumentSnapshot> call = apiInterface.listItemViewData(this.docName,id);
        call.enqueue(new Callback<ViewDocumentSnapshot>() {
            @Override
            public void onResponse(Call<ViewDocumentSnapshot> call, Response<ViewDocumentSnapshot> response) {
                DocumentSnapshot doc = response.body();
                setValue(doc);
                if (valueChangedListner != null) {
                    valueChangedListner.onChanged(doc, DocumentLookupInputView.this.getId());
                }
            }
            @Override
            public void onFailure(Call<ViewDocumentSnapshot> call, Throwable t) {
                call.cancel();
                Log.e(TAG, t.getMessage());
                throw new RuntimeException("Stub!");
            }
        });
    }
}
