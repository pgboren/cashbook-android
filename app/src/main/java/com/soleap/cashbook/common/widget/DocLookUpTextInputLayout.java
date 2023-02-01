package com.soleap.cashbook.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DocLookUpTextInputLayout extends TextInputLayout {

    private TextInputEditText editText;

    public DocLookUpTextInputLayout(@NonNull Context context) {
        this(context, null);
    }

    public DocLookUpTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, com.google.android.material.R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox);
    }

    public DocLookUpTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //removeAllViews();
        setWillNotDraw(false);
        editText = new TextInputEditText(getContext());
        createEditBox(editText);
    }

    private void createEditBox(TextInputEditText editText) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        editText.setPadding(0,10,0,0);
        editText.setLayoutParams(layoutParams);
        addView(editText);
    }


}
