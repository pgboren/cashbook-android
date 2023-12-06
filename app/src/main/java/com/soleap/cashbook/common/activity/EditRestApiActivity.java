package com.soleap.cashbook.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.fragment.DocFormFragment;
import com.soleap.cashbook.common.global.EventHandler;
import com.soleap.cashbook.common.util.ResourceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditRestApiActivity<T extends Document> extends RestApiModelFormActivity<T> {

    private static final String TAG = "EditRestApiActivity";
    public static final String KEY_DOC_ID = "key_doc_id";
    private DocFormFragment formFragment;

    protected T model;

    protected String modelId = null;

    @Override
    protected String getViewTitle() {
        return ResourceUtil.getStringResourceByName(this, documentInfo.getDocEditViewDef().getTitle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelId = getIntent().getExtras().getString(KEY_DOC_ID);
        if (modelId == null) {
            throw new IllegalArgumentException("Must pass extra " + KEY_DOC_ID);
        }
        loadModel(modelId);
    }

    @Override
    protected boolean validation() {
        isValid = formFragment.validation();
        return super.validation();
    }

    @Override
    protected void readInputData(T document) {
        formFragment.readInputData(document);
    }

    @Override
    protected void setViewContent() {
        setContentView(R.layout.activity_form_bsdoc);
        initInputView();
    }

    @Override
    protected void onError(JSONObject errorObject) throws JSONException {
    }

    @Override
    protected void resetFields() {
    }

    protected void initInputView() {
        try {
            Class fragmentClass = documentInfo.getDocAddNewDef().getFormFragmentViewClass();
            formFragment = (DocFormFragment) fragmentClass.newInstance();
            formFragment.setValueChangeListner(new DocFormFragment.ValueChangeListner() {
                @Override
                public void onValueChanged() {
                    validation();
                }
            });
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.form_container,formFragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
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
                Call<Map<String, Object>> call = apiInterface.patch(documentInfo.getName(), model.get_id(), model.toMap());
                call.enqueue(new Callback<Map<String, Object>>() {
                    @Override
                    public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                        hideLoadingScreen();
                        Map<String, Object> result = (Map<String, Object>) response.body();
                        EventHandler.getInstance().notifyDocumentChanged(documentInfo.getName(), result.get("id").toString());
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                        hideLoadingScreen();
                        EventHandler.getInstance().notifyDocumentChangedError(documentInfo.getName(), t);
                        Log.e(TAG, t.getMessage());
                        finish();
                    }
                });
            }
        }
        catch (Exception ex) {
            hideLoadingScreen();
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    private void loadModel(String docId) {
        Call<Document> call = apiInterface.get(documentInfo.getName().toLowerCase(), docId);
        call.enqueue(new Callback<Document>() {
            @Override
            public void onResponse(Call<Document> call, Response<Document> response) {
                model = (T) response.body();
                formFragment.assignValueToForm(model);
                validation();
            }

            @Override
            public void onFailure(Call<Document> call, Throwable t) {
                String msg = t.getMessage();
            }
        });
    }
}
