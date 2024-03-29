package com.soleap.cashbook.common.widget.view;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.DimensionUtils;
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
        valueContainer.setPadding(DimensionUtils.dpToPx(activity, 5),DimensionUtils.dpToPx(activity, 7),DimensionUtils.dpToPx(activity, 5),DimensionUtils.dpToPx(activity, 7));
        valueContainer.setOrientation(LinearLayout.HORIZONTAL);
        TextView textLabel = new TextView(activity);
        textLabel.setTextSize(12);
        textLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.3f));
        textLabel.setTextColor(activity.getColor(R.color.secondaryTextColor));
        textLabel.setText(ResourceUtil.getStringResourceByName(activity, data.getLabel().toLowerCase()));
        valueContainer.addView(textLabel);

        TextView textValue = new TextView(activity);
        textValue.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f));
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
