package com.soleap.cashbook.widget.paymentoption;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.OnValueChanged;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;

public class PaymentOptionBottomSheetView extends LinearLayout implements PaymentOptionBottomSheetItemClickListener {

    private static final String TAG = "PaymentOptionBottomSheetView";
    private String value = null;
    private String title = "";
    private BottomSheetMenu optionMenu;
    private OnValueChanged onValueChangedListener = null;
    private TextInputEditText txtValue;
    private FragmentManager fragmentManager;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setOnValueChangedListener(OnValueChanged onValueChangedListener) {
        this.onValueChangedListener = onValueChangedListener;
    }

    public PaymentOptionBottomSheetView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PaymentOptionView, 0, 0);
        this.title = a.getString(R.styleable.PaymentOptionView_potv_label);
        boolean isRequired = a.getBoolean(R.styleable.PaymentOptionView_potv_require, false);
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.payment_optons_textinputedittext, this, true);

        txtValue = findViewById(R.id.txt_value);

        TextInputLayout textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout.setHint(title);
        textInputLayout.setErrorEnabled(isRequired);
        textInputLayout.setHelperText(context.getText(R.string.require));
        textInputLayout.setEndIconOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showPaymentOptionsMenu();
            }
        });

        textInputLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showPaymentOptionsMenu();
            }
        });
    }

    private void showPaymentOptionsMenu() {
        PaymentOptionBottomSheet bottomSheet = new PaymentOptionBottomSheet(PaymentOptionBottomSheetView.this);
        bottomSheet.show(fragmentManager, TAG);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @


            Override
    public void onItemClick(String option) {

        txtValue.setText(ResourceUtil.getStringResourceByName(getContext(), option));
        value = option;
    }

}
