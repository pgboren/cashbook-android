package com.soleap.cashbook.activity;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.RestApiModelFormActivity;
import com.soleap.cashbook.common.document.Document;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class DocAddNewActivity extends RestApiModelFormActivity<Document> {

    private static final String TAG = "DocAddNewActivity";

    protected String getViewTitle() {
        return "add_new_" + documentName;
    }

    @Override
    protected void setViewContent() {
        setContentView(R.layout.activity_form_bsdoc);
        initInputView();
    }

    protected abstract void initInputView();

    @Override
    protected void resetFields() {

    }

    @Override
    protected void onError(JSONObject errorObject) throws JSONException {

    }
}
