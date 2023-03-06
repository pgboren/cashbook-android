package com.soleap.cashbook.activity.task;

import android.view.View;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;

public class AgileTaskViewActivity extends ViewDataActivity<DocumentSnapshot> {

    private static final String TAG = "AgileTaskViewActivity";

    @Override
    public void setContentView(View view) {
        setContentView(R.layout.activity_view_agile_task);
    }


}