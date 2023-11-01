package com.soleap.cashbook.common.widget.input;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.widget.OnValueChangedListner;

public abstract class BaseButtomSheetInputView<T> extends LinearLayout {

    protected OnValueChangedListner valueChangedListner;

    public void setValueChangedListner(OnValueChangedListner valueChangedListner) {
        this.valueChangedListner = valueChangedListner;
    }

    private View rootView;

    protected TextView txtValue;

    protected String label;

    @Override
    public View getRootView() {
        return rootView;
    }

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        onValueSet();
        if (valueChangedListner != null) {
            valueChangedListner.onChanged(value, getId());
        }
        updateDisplayValue();
    }

    public BaseButtomSheetInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BaseButtomSheetInputView, 0, 0);
        processAttributeSet(context, attrs, a);
        a.recycle();
    }

    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        this.label = a.getString(R.styleable.BaseButtomSheetInputView_biv_label);
        Drawable icon = a.getDrawable(R.styleable.BaseButtomSheetInputView_biv_iconDrawable);
        boolean arrow_right = a.getBoolean(R.styleable.BaseButtomSheetInputView_biv_arrow_right, false);
        this.rootView = inflatInputLayout(context);
        TextView txtLable = rootView.findViewById(R.id.txt_label);
        ImageButton imvIcon = rootView.findViewById(R.id.img_icon);
        ImageButton endIcon = rootView.findViewById(R.id.btn_arrow_right);
        txtValue = rootView.findViewById(R.id.txt_value);
        if (arrow_right) {
            endIcon.setVisibility(View.VISIBLE);
        }
        else {
            endIcon.setVisibility(View.GONE);
        }
        imvIcon.setImageDrawable(icon);
        txtLable.setText(label);
    }

    protected View inflatInputLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(getInputLayout(), this, true);
        View touchedHandlerContainer = view.findViewById(R.id.touch_handler_container);
        touchedHandlerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseButtomSheetInputView.this.onClick();
            }
        });
        return view;
    }
    protected abstract void onClick();
    protected abstract void onValueSet();

    protected abstract void updateDisplayValue();
    protected int getInputLayout() {
        return R.layout.base_buttom_sheet_inputview;
    }
}
