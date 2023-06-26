package com.soleap.cashbook.common.activity;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.RestApiModelFormActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.util.ResourceUtil;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class DocAddNewActivity extends RestApiModelFormActivity<Document> {

    private static final String TAG = "DocAddNewActivity";

    protected String getViewTitle() {
        return ResourceUtil.getStringResourceByName(this, documentInfo.getDocAddNewDef().getTitle());
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
