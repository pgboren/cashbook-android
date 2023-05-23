package com.soleap.cashbook.common.widget.input;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.widget.dialog.FullscreenDialog;

public abstract class DialogInputView<T> extends LinearLayout {

    private FragmentManager fragmentManager;

    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        onValueSetted(value);
    }

    protected abstract void onValueSetted(T value);

    protected abstract String getString();

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public DialogInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(getResourceViewId(), this, true);
        View rootView = findViewById(R.id.root_view);
        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
              showDialogInputView();
            }
        });
    }

    protected abstract int getResourceViewId();

    protected abstract FullscreenDialog createDialog();

    protected void showDialogInputView() {
        FullscreenDialog<T> dialog = createDialog();
        (dialog).setCallback(new FullscreenDialog.Callback<T>() {
            @Override
            public void onActionClick(T value) {
                setValue(value);
            }
        });
        dialog.setTitle(getString());
        dialog.setValue(value);
        dialog.show(fragmentManager, "tag");
    }

}
