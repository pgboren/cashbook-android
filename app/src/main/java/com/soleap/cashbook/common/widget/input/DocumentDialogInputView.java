package com.soleap.cashbook.common.widget.input;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.widget.dialog.FullscreenDialog;
import com.soleap.cashbook.common.widget.dialog.TextInputDialog;
import com.soleap.cashbook.restapi.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class DocumentDialogInputView extends DialogInputView<String> {

    private static final String  TAG = "DocumentDialogInputView";

    private String documentName;

    protected APIInterface apiInterface;

    protected abstract String getDocumentName();

    public DocumentDialogInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.documentName = getDocumentName();
    }

    protected Call<DocumentSnapshot> createGetApi(String docId) {
        return apiInterface.get(documentName.toLowerCase(), docId);
    }

    protected void getDocument(String docId) {
        Call<DocumentSnapshot> call = createGetApi(docId);
        call.enqueue(new Callback<DocumentSnapshot>() {
            @Override
            public void onResponse(Call<DocumentSnapshot> call, Response<DocumentSnapshot> response) {
                if (response.isSuccessful()) {

                }
            }
            @Override
            public void onFailure(Call<DocumentSnapshot> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                call.cancel();
            }
        });
    }

    @Override
    protected void onValueSetted(String docId) {
        getDocument(docId);
    }

}
