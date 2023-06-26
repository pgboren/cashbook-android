package com.soleap.cashbook.common.widget.view;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

public abstract class FieldCreator implements ActivityEventListner {

    public final static int PICK_IMAGE_REQUEST_CODE = 104;

    protected ViewDataActivity activity;
    protected ViewData fieldData;
    protected ViewSetterFactory viewSetterFactory;
    protected APIInterface apiInterface;

    public abstract View createView();

    public FieldCreator(ViewDataActivity activity, ViewData fieldData) {
        this.activity = activity;
        this.fieldData = fieldData;
        viewSetterFactory = ViewSetterFactory.getInstance(activity.findViewById(R.id.content_container));
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
    }

}
