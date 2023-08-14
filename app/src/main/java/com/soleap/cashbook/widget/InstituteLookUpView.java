package com.soleap.cashbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.util.MedialUtils;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.common.widget.BaseLookUpInputView;
import com.soleap.cashbook.document.Institute;

public class InstituteLookUpView extends BaseLookUpInputView<Document> {

    public InstituteLookUpView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    protected int getInputLayout() {
        return R.layout.input_lookup_institute_view;
    }

    @Override
    protected void renderView() {
        DocumentSnapshot doc = (DocumentSnapshot) getValue();
        String name = doc.getDataValue("name").getValue().toString();
        String latinName = doc.getDataValue("latinname").getValue().toString();
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(this);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_name).setString(name);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_latin_name).setString(latinName);
        ImageView imgLogo = findViewById(R.id.img_logo);
        MedialUtils.loadImage(getContext(), doc.getDataValue("logo").getValue().toString(), imgLogo);
    }

}
