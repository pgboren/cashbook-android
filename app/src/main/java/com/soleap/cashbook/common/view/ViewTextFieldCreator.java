package com.soleap.cashbook.common.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.value.ViewType;

public class ViewTextFieldCreator extends FieldCreator {

    public ViewTextFieldCreator(ViewDataActivity activity, ViewData fieldData) {
        super(activity, fieldData);
    }

    @Override
    public View createView() {
        ViewData data = fieldData;
        LinearLayout valueContainer = new LinearLayout(activity);
        valueContainer.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 20,0,20);
        valueContainer.setLayoutParams(params);
        valueContainer.setOrientation(LinearLayout.HORIZONTAL);
        TextView textLabel = new TextView(activity);
        textLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.4f));
        textLabel.setTextColor(activity.getColor(R.color.secondaryTextColor));
        textLabel.setText(ResourceUtil.getStringResourceByName(activity, data.getLabel().toLowerCase()));
        valueContainer.addView(textLabel);

        TextView textValue = new TextView(activity);
        textValue.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.6f));
        textValue.setTextColor(activity.getColor(R.color.secondaryTextColor));
        textValue.setTextColor(activity.getColor(R.color.secondaryTextColor));
        valueContainer.addView(textValue);
        if (data.getValue() != null) {
            setValue(textValue, data);
        }
        return valueContainer;
    }


    protected void setValue(TextView textValue, ViewData data) {
        viewSetterFactory.create(ViewType.TEXTVIEW, textValue).setString(data.getValue().toString());
    }

}
