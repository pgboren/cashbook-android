package com.soleap.cashbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.util.NumberUtils;
import com.soleap.cashbook.common.widget.input.BaseButtomSheetInputView;
import com.soleap.cashbook.widget.fragment.BottomSheetFragmentListner;
import com.soleap.cashbook.widget.fragment.NumberInputBottomSheetFragment;

public class NumberInputView extends BaseButtomSheetInputView<Double> implements BottomSheetFragmentListner {

    private boolean isShowDialog = false;
    private String format;

    private FragmentManager fragmentManager;

    private NumberInputBottomSheetFragment bottomSheetFragment;

    public NumberInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setValue((double) 0);
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        fragmentManager = fragmentActivity.getSupportFragmentManager();
    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        this.format = a.getString(R.styleable.BaseButtomSheetInputView_biv_format);
    }

    @Override
    protected void onClick() {
        showInputDialog();
    }

    @Override
    protected void onValueSet() {
    }

    @Override
    protected void updateDisplayValue() {;
        txtValue.setText(NumberUtils.formatDouble(format, getValue().doubleValue()));
    }

    private void showInputDialog() {
        if (!isShowDialog) {
            bottomSheetFragment = new NumberInputBottomSheetFragment(true, this.label);
            bottomSheetFragment.setListner(this);
            bottomSheetFragment.setValue(getValue());
            bottomSheetFragment.show(fragmentManager, "Expanded");
            isShowDialog = true;
        }
    }

    @Override
    public void onDialogClosed() {
        double value = bottomSheetFragment.getValue();
        setValue(value);
        isShowDialog = false;
    }

    @Override
    protected int getInputLayout() {
        return R.layout.number_inputview;
    }
}
