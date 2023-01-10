package com.soleap.cashbook.common.view;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.common.value.ViewType;

public abstract class ViewCommunicationFieldCreator extends FieldCreator {

    public ViewCommunicationFieldCreator(ViewDataActivity activity, ViewData fieldData) {
        super(activity, fieldData);
    }

    @Override
    public View createView() {
        final ViewData data = fieldData;
        Activity activity = (Activity) this.activity;
        View contentContainer = activity.findViewById(R.id.content_container);
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(contentContainer);
        LinearLayout fieldContainer = new LinearLayout(this.activity);
        fieldContainer.setOrientation(LinearLayout.HORIZONTAL);
        TextView textLabel = new TextView(this.activity);
        textLabel.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.4f));
        params.gravity =  Gravity.CENTER_VERTICAL;
        textLabel.setLayoutParams(params);
        textLabel.setTextColor(this.activity.getColor(R.color.secondaryTextColor));
        textLabel.setText(ResourceUtil.getStringResourceByName(this.activity, data.getLabel().toLowerCase()));
        fieldContainer.addView(textLabel);

        LinearLayout valueContainer = new LinearLayout(this.activity);
        valueContainer.setGravity(Gravity.CENTER_VERTICAL);
        valueContainer.setPadding(20,20,20,20);
        valueContainer.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.6f));
        valueContainer.setOrientation(LinearLayout.HORIZONTAL);
        TypedValue outValue = new TypedValue( );
        this.activity.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        valueContainer.setBackground(this.activity.getDrawable(R.drawable.ripple));

        int iconSize = (int) this.activity.getResources().getDimension(R.dimen.view_field_contact_icon_size);
        LinearLayout.LayoutParams valueParams = new LinearLayout.LayoutParams(iconSize, iconSize);
        valueParams.gravity = Gravity.CENTER_VERTICAL;
        ImageView img = new ImageView(this.activity);
        img.setLayoutParams(valueParams);
        img.setImageDrawable(this.activity.getDrawable(getIcon()));
        valueContainer.addView(img);

        valueParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        valueParams.setMargins(20,0,0,0);
        valueParams.gravity = Gravity.CENTER_VERTICAL;
        TextView textValue = new TextView(this.activity);
        textValue.setGravity(Gravity.CENTER_VERTICAL);
        textValue.setLayoutParams(valueParams);
        textValue.setTextColor(this.activity.getColor(R.color.view_comm_field_color));

        valueContainer.addView(textValue);

        fieldContainer.addView(valueContainer);
        fieldContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUserClicked(data.getValue().toString());
            }
        });

        if (data.getValue() != null) {
            viewSetterFactory.create(ViewType.TEXTVIEW, textValue).setString(data.getValue().toString());
        }

        return fieldContainer;
    }

    protected abstract void onUserClicked(String clickValue);

    protected abstract int getIcon();

}
