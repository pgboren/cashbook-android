package com.soleap.cashbook.widget.paymentoption;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.OnValueChanged;

public class PaymentOptionView extends LinearLayout implements PaymentOptionBottomSheetItemClickListener {

    private static String TAG = "PaymentOptionView";

    private OnValueChanged onValueChangedListner = null;

    private String value = null;

    private TextView tvValue;

    private FragmentManager fragmentManager;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setOnValueChangedListner(OnValueChanged onValueChangedListner) {
        this.onValueChangedListner = onValueChangedListner;
    }

    public PaymentOptionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PaymentOptionView, 0, 0);
        String label = a.getString(R.styleable.PaymentOptionView_potv_label);
        int colorCode = a.getColor(R.styleable.PaymentOptionView_potv_text_color, context.getColor(R.color.label));
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.payment_option_view, this, true);

        View rootView = findViewById(R.id.root_view);
        TextView tvLabel = findViewById(R.id.label);
        tvValue = findViewById(R.id.value);
        tvValue.setTextColor(colorCode);
        tvLabel.setText(label);
        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentOptionBottomSheet paymentOptionBottomSheet = new PaymentOptionBottomSheet(PaymentOptionView.this);
                paymentOptionBottomSheet.show(fragmentManager, TAG);
            }
        });
        setValue("FULL_PAYMENT");

    }

    public void setValue(String value) {
        this.value = value;
        tvValue.setText(ResourceUtil.getStringResourceByName(getContext(), value));
        if (onValueChangedListner != null) {
            onValueChangedListner.onChanged(value);
        }
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public void onItemClick(String option) {
        setValue(option);
    }
}
