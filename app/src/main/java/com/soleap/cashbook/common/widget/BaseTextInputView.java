package com.soleap.cashbook.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;

public abstract class BaseTextInputView<T> extends LinearLayout {

    protected boolean errorEnabled;

    protected T value;

    protected OnValueChangedListner valueChangedListner;

    protected TextInputLayout textInputLayout;

    protected TextInputEditText editText;

    public void setValueChangedListner(OnValueChangedListner valueChangedListner) {
        this.valueChangedListner = valueChangedListner;
    }

    protected TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            TextInputEditText editText = findViewById(R.id.editText);
            onTextChange(editText.getText().toString());
        }
    };

    protected abstract void onTextChange(String text);

    public void setValue(T value) {
        this.value = value;
        onValueSet(value);
    }

    public T getValue() {
        return value;
    }

    protected void onValueSet(T value) {
        TextInputEditText editText = findViewById(R.id.editText);
        editText.removeTextChangedListener(textWatcher);
        editText.setText( value == null ? "" : value.toString());
        editText.addTextChangedListener(textWatcher);
    }

    public abstract boolean validate();

    protected int getInputLayout() {
        return R.layout.input_edittext;
    }

    protected void inflatInputLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(getInputLayout(), this, true);
    }

    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        String hint = a.getString(R.styleable.EditTextInputView_ev_hint);
        errorEnabled = a.getBoolean(R.styleable.EditTextInputView_ev_errorEnabled, false);
        String helperText = a.getString(R.styleable.EditTextInputView_ev_helperText);
        int lines = a.getInt(R.styleable.EditTextInputView_ev_lines, 1);
        int minLines = a.getInt(R.styleable.EditTextInputView_ev_minLines, 1);
        int maxLines = a.getInt(R.styleable.EditTextInputView_ev_maxLines, 1);
        inflatInputLayout(context);

        textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout.setHint(hint);
        textInputLayout.setErrorEnabled(errorEnabled);
        textInputLayout.setHelperText(helperText);

        editText = findViewById(R.id.editText);
        editText.setLines(lines);
        editText.addTextChangedListener(textWatcher);
        editText.setMinLines(minLines);
        editText.setMaxLines(maxLines);
    }

    public BaseTextInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditTextInputView, 0, 0);
        processAttributeSet(context, attrs, a);
        a.recycle();
    }

}
