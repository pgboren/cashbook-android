package com.soleap.cashbook.common.widget.lookup;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.TextEnumValueDocument;

import java.util.List;

public class TextEnumSheetLookUpInputView extends BottomSheetLookUpInputView<TextEnumValueDocument> {

    private List<TextEnumValueDocument> docs;

    public void setTexts(List<TextEnumValueDocument> docs) {
        this.docs = docs;
        setValue(null);
    }

    public TextEnumSheetLookUpInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected RecyclerView.Adapter createAdapter() {
        TextEnumRecyclerViewAdapter adapter = new TextEnumRecyclerViewAdapter();
        adapter.setDataSet(docs);
        return adapter;
    }

    @Override
    protected int getLayoutInputView() {
        return R.layout.text_lookup_input_view;
    }

    @Override
    protected void onValueSet(TextEnumValueDocument value) {
        TextInputEditText editText = findViewById(R.id.editText);
        editText.removeTextChangedListener(textWatcher);
        if (value != null) {
            editText.setText(value.toString());
        }
        else {
            editText.getText().clear();
        }
        editText.addTextChangedListener(textWatcher);
    }

}
