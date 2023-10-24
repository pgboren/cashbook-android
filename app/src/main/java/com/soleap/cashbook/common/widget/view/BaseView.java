package com.soleap.cashbook.common.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

public class BaseView extends LinearLayout {

    public BaseView(Context context) {
        super((Context)context);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
