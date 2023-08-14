package com.soleap.cashbook.common.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.soleap.cashbook.R;

public abstract class PhotoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
    }

}
