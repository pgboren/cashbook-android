package com.soleap.cashbook.common.view;

import android.content.Intent;

import androidx.annotation.Nullable;

public interface ActivityEventListner {

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent);
}
