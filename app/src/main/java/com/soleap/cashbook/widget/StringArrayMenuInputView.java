package com.soleap.cashbook.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;
import com.soleap.cashbook.common.widget.bottomsheetmenu.Menu;
import com.soleap.cashbook.common.widget.bottomsheetmenu.MenuItem;
import com.soleap.cashbook.common.widget.input.BaseButtomSheetInputView;
import java.util.Calendar;

public class StringArrayMenuInputView extends BaseButtomSheetInputView<String> {

    private String[] stringValues = new String[0];
    private Dialog bottomSheetMenu;
    private Menu menu;

    public StringArrayMenuInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        super.processAttributeSet(context, attrs, a);
        int stringArrayReference = a.getResourceId(R.styleable.BaseButtomSheetInputView_biv_strings_array, 0);
        String defaultValue  = a.getString(R.styleable.BaseButtomSheetInputView_biv_default);
        if (stringArrayReference != 0) {
            stringValues = getResources().getStringArray(stringArrayReference);
        }
        menu = new Menu();
        menu.setTitle("Payment Option");
        for (String item: stringValues) {
            menu.addItem(item, ResourceUtil.getStringResourceByName(getContext(), item));
        }
        bottomSheetMenu = new BottomSheetMenu(getContext(), new BottomSheetMenu.BottomSheetMenuItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
                String selectedValue = item.getId();
                setValue(selectedValue);
            }
        }, menu, R.layout.text_menu_item);
        if (defaultValue != null) {
            setValue(defaultValue);
        }
    }

    @Override
    protected void onClick() {
        bottomSheetMenu.show();
    }

    @Override
    protected void onValueSet() {
    }

    @Override
    protected void updateDisplayValue() {
        txtValue.setText(ResourceUtil.getStringResourceByName(getContext(), getValue()));
    }

}
