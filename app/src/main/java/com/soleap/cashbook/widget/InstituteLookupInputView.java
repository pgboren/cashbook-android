package com.soleap.cashbook.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.util.MedialUtils;
import com.soleap.cashbook.common.widget.doclookup.DocumentListBottomSheetFragment;
import com.soleap.cashbook.common.widget.doclookup.DocumentListBottomSheetFragmentEventListner;
import com.soleap.cashbook.common.widget.input.BaseButtomSheetInputView;
import com.soleap.cashbook.document.Institute;
import com.soleap.cashbook.view.DocumentInfo;

public class InstituteLookupInputView extends DocLookupInputView {

    private ImageView imgLogo;

    public InstituteLookupInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        this.imgLogo = getRootView().findViewById(R.id.img_logo);
        if (getValue() == null) {
            this.imgLogo.setVisibility(View.GONE);
        }
    }

    @Override
    protected void updateDisplayValue() {
        super.updateDisplayValue();
        Institute institute = (Institute) getValue();
        if (institute.getLogoMedia() != null) {
            this.imgLogo.setVisibility(View.VISIBLE);
            MedialUtils.loadImage(getContext(), institute.getLogoMedia().getPath(), imgLogo);
        }
        else {
            this.imgLogo.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getInputLayout() {
        return R.layout.institute_buttom_sheet_inputview;
    }
}
