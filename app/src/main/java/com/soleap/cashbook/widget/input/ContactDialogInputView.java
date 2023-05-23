package com.soleap.cashbook.widget.input;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.soleap.cashbook.common.widget.dialog.FullscreenDialog;
import com.soleap.cashbook.common.widget.input.DocumentDialogInputView;
import com.soleap.cashbook.document.DocumentName;

public class ContactDialogInputView extends DocumentDialogInputView {

    @Override
    protected String getString() {
        return null;
    }

    @Override
    protected String getDocumentName() {
        return DocumentName.CONTACT;
    }

    public ContactDialogInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getResourceViewId() {
        return 0;
    }

    @Override
    protected FullscreenDialog createDialog() {
        return null;
    }
}
