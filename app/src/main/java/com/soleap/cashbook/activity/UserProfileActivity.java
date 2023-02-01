package com.soleap.cashbook.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BackPressActivity;
import com.soleap.cashbook.content.AppPrefrences;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.document.User;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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