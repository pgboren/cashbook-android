package com.soleap.cashbook.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.DocItemsViewActivity;

public class SaleOrderitemViewActivity extends DocItemsViewActivity {

    @Override
    protected void setViewContent() {
        setContentView(R.layout.activity_form_sale_order_item_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            setSupportActionBar(toolbar);
        }

        setTitle(getString(R.string.add_sale_order_item));
    }

}
