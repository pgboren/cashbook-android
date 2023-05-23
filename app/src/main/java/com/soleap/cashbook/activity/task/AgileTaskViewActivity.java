package com.soleap.cashbook.activity.task;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.widget.input.ContactDialogInputView;
import com.soleap.cashbook.widget.paymentoption.PaymentOptionView;

public class AgileTaskViewActivity extends ViewDataActivity<DocumentSnapshot> {

    private static final String TAG = "AgileTaskViewActivity";

    @Override
    protected void setViewContent() {
        setContentView(R.layout.activity_view_agile_task);
    }

    @Override
    protected void renderViewData(DocumentSnapshot doc) {
        LinearLayout rootView = findViewById(R.id.content_container);
        setTitle(doc.getDataValue("name").getValue().toString());
        ContactDialogInputView textInputView = findViewById(R.id.view_task_name);
        textInputView.setFragmentManager(getSupportFragmentManager());
        ((TextView)findViewById(R.id.view_task_phonenumber)).setText(doc.getDataValue("phonenumber").getValue().toString());
        ((TextView)findViewById(R.id.view_task_vehicle)).setText(doc.getDataValue("item").getDataValue("name").getValue().toString());
        ((TextView)findViewById(R.id.view_task_price)).setText(doc.getDataValue("price").getValue().toString());
        PaymentOptionView paymentOptionView = findViewById(R.id.paymentOptionView);
        paymentOptionView.setFragmentManager(this.getSupportFragmentManager());

    }
}