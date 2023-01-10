package com.soleap.cashbook.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class EditRestApiActivity<T extends Document> extends RestApiModelFormActivity<T> implements DocumentSnapshotRepository.OnDocumentChangedListner {

    private static final String TAG = "EditRestApiActivity";
    public static final String KEY_MODEL_ID = "key_model_id";
    public static final String KEY_MODEL = "key_model";

    @Override
    protected String getViewTitle() {
        return "edit" + documentName;
    }

    protected T model;
    protected String modelId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RepositoryFactory.create().get(documentName).addOnChangedDocumentListner(documentName, this);
        modelId = getIntent().getExtras().getString(KEY_MODEL_ID);
        if (modelId == null) {
            throw new IllegalArgumentException("Must pass extra " + KEY_MODEL_ID);
        }
        loadModel(modelId);


    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
        return true;
    }

    @Override
    protected void save() {
        try {
            if (validation()) {
                showLoadingScreen();
                readInputData(model);
                RepositoryFactory.create().get(documentName).update(model);
            }
        }
        catch (Exception ex) {
            Log.e("Error", ex.getMessage(), ex);
        }
    }

    @Override
    public void onChanged(DocumentSnapshot documentSnapshot) {
        RepositoryFactory.create().get(documentName).removeOnChangedDocumentListner(documentName, this);
        finish();
    }

    @Override
    protected void onError(JSONObject errorObject) throws JSONException {

    }

    private void loadModel(String modelId) {
        showLoadingScreen();
        RepositoryFactory.create().get(documentName).get(documentName, modelId, new DocumentSnapshotRepository.OnGetDocumentListner() {
            @Override
            public void onGet(Document document) {
                model = (T)document;
                assignValueToForm(model);
                validation();
                hideLoadingScreen();
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, t.getMessage());
                hideLoadingScreen();
            }
        });

    }

    protected abstract void assignValueToForm(T model);

}
