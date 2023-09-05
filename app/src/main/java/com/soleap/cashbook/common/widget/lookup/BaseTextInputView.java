package com.soleap.cashbook.common.widget.lookup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;

public abstract class BaseTextInputView<T> extends LinearLayout {

    protected boolean errorEnabled;

    protected String error;
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

    protected abstract void onEndIconClickedListner();

    protected abstract int getLayoutInputView();

    public void setValue(T value) {
        this.value = value;
        onValueSet(value);
        if (valueChangedListner != null) {
            valueChangedListner.onChanged(value, getId());
        }
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

    public boolean validate() {
        if (errorEnabled) {
            T value = getValue();
            if (value == null) {
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setErrorIconDrawable(null);
                textInputLayout.setError(error);
                return false;
            }
            else {
                textInputLayout.setErrorEnabled(false);
                textInputLayout.setError("");
            }
        }
        return true;
    }

    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        String hint = a.getString(R.styleable.EditTextInputView_ev_hint);
        Drawable endIcon = a.getDrawable(R.styleable.EditTextInputView_ev_endIconDrawable);
        Drawable startIcon = a.getDrawable(R.styleable.EditTextInputView_ev_startIconDrawable);
        errorEnabled = a.getBoolean(R.styleable.EditTextInputView_ev_errorEnabled, false);
        error = a.getString(R.styleable.EditTextInputView_ev_error);
        int lines = a.getInt(R.styleable.EditTextInputView_ev_lines, 1);
        int minLines = a.getInt(R.styleable.EditTextInputView_ev_minLines, 1);
        int maxLines = a.getInt(R.styleable.EditTextInputView_ev_maxLines, 1);
        boolean readOnly = a.getBoolean( R.styleable.EditTextInputView_ev_readonly, false);

        String helperText = a.getString(R.styleable.EditTextInputView_ev_helperText);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(getLayoutInputView(), this, true);

        textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout.setHint(hint);
        textInputLayout.setHelperText(helperText);

        if (startIcon != null) {
            textInputLayout.setStartIconDrawable(startIcon);
        }

        if (endIcon != null) {
            textInputLayout.setEndIconDrawable(endIcon);
            textInputLayout.setEndIconOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEndIconClickedListner();
                }
            });
        }

        editText = findViewById(R.id.editText);
        editText.setLines(lines);
        editText.addTextChangedListener(textWatcher);
        editText.setMinLines(minLines);
        editText.setMaxLines(maxLines);
        editText.setInputType(InputType.TYPE_NULL);
        editText.setTextIsSelectable(false);
    }

    public BaseTextInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditTextInputView, 0, 0);
        processAttributeSet(context, attrs, a);
        a.recycle();
    }

}
