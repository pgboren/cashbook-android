package com.soleap.cashbook.common.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BackPressActivity extends AppCompatActivity {

    protected ActionBar toolbar;

    protected void setTitle() {
        toolbar.setTitle(getTitle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreatingBegin();
        onCreating();
        onCreatingFinished();
    }

    protected void configureActionBar() {
        toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true);
            setTitle();
        }
    }

    protected void onCreatingBegin() {

    }

    protected void onCreating() {
        setViewContent();
        configureActionBar();
    }

    protected void onCreatingFinished() {

    }

    protected abstract void setViewContent();

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}