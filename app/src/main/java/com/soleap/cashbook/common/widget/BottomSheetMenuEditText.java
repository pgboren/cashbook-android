package com.soleap.cashbook.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;
import com.soleap.cashbook.common.widget.bottomsheetmenu.Menu;
import com.soleap.cashbook.common.widget.bottomsheetmenu.MenuItem;

public class BottomSheetMenuEditText extends BaseEditTextInputView<String> implements BottomSheetMenu.BottomSheetMenuItemClickListener {
    private AppCompatActivity activity;

    private Menu menu;

    private Dialog bottomSheetMenu;

    public void setMenu(Menu menu) {
        this.menu = menu;
        bottomSheetMenu = new BottomSheetMenu(getContext(), this, menu, R.layout.text_menu_item);
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onTextChange(String text) {

    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        Drawable endIconDrawable = a.getDrawable(R.styleable.EditTextInputView_ev_endIconDrawable);
        if (endIconDrawable != null) {
            textInputLayout.setEndIconDrawable(endIconDrawable);
        }
    }

    public BottomSheetMenuEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        editText.setTextIsSelectable(false);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetMenu.show();
            }
        });
    }
    @Override
    protected void onValueSet(String value) {

    }

    @Override
    public boolean validate() {
        if (!errorEnabled) {
            return true;
        }
        return getValue() != null ?  true : false;
    }

    @Override
    public void onItemClick(MenuItem item) {
        setValue(item.getId());
        editText.setText(item.getTitle());
    }
}
