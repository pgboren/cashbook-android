package com.soleap.cashbook.activity;


import androidx.appcompat.widget.Toolbar;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BackPressActivity;

public class UserProfileActivity extends BackPressActivity {

    @Override
    protected void setViewContent() {
        setContentView(R.layout.user_profile_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            setSupportActionBar(toolbar);
        }
        toolbar.setTitle(getString(R.string.user_profile));
    }

}