package com.soleap.cashbook.common.widget.input;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.widget.dialog.FullscreenDialog;
import com.soleap.cashbook.common.widget.dialog.TextInputDialog;

public class TerxtDialogInputView extends DialogInputView<String> {

    @Override
    protected void onValueSetted(String value) {

    }

    @Override
    protected String getString() {
        return value;
    }

    public TerxtDialogInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getResourceViewId() {
        return R.layout.text_input_view;
    }

    @Override
    protected FullscreenDialog createDialog() {
        return new TextInputDialog(R.layout.dialog_textinput);
    }


}
