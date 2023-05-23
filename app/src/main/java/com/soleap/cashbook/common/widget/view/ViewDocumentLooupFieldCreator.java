package com.soleap.cashbook.common.widget.view;

import android.app.Activity;
import android.content.Intent;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocLookUpActivity;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.document.DocumentName;

import java.util.Locale;

public abstract class ViewDocumentLooupFieldCreator extends FieldCreator {

    private final String documentName;
    protected TextView textName;
    private int requestCode;
    private int value_layout;

    public ViewDocumentLooupFieldCreator(ViewDataActivity activity, String documentName, int requestCode, ViewData fieldData, int value_layout) {
        super(activity, fieldData);
        this.documentName = documentName;
        this.requestCode = requestCode;
        this.value_layout = value_layout;
    }

    @Override
    public View createView() {
        final ViewData data = fieldData;

        Activity activity = (Activity) this.activity;
        View contentContainer = activity.findViewById(R.id.content_container);
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(contentContainer);
        LinearLayout fieldContainer = new LinearLayout(this.activity);
        fieldContainer.setOrientation(LinearLayout.VERTICAL);
        TextView textLabel = new TextView(this.activity);
        textLabel.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        params.gravity =  Gravity.CENTER_VERTICAL;
        textLabel.setLayoutParams(params);
        textLabel.setTextColor(this.activity.getColor(R.color.secondaryTextColor));
        textLabel.setText(ResourceUtil.getStringResourceByName(this.activity, data.getLabel().toLowerCase()));
        textLabel.setTextSize(this.activity.getResources().getDimension(R.dimen.view_field_lookup_label));
        fieldContainer.addView(textLabel);

        LinearLayout valueContainer = new LinearLayout(this.activity);
        valueContainer.setGravity(Gravity.CENTER_VERTICAL);
        valueContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        valueContainer.setOrientation(LinearLayout.HORIZONTAL);

        TypedValue outValue = new TypedValue( );
        this.activity.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        valueContainer.setBackground(this.activity.getDrawable(R.drawable.ripple));
        if (data.getValue() != null) {
            inflateLookupView(valueContainer);
            fieldContainer.addView(valueContainer);
            fieldContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lookupDocument(documentName, requestCode);
                }
            });
        }

        ImageView imgArrowRight = new ShapeableImageView(this.activity);
        LinearLayout.LayoutParams valueParams = new LinearLayout.LayoutParams(50, 50);
        valueParams.setMargins(0,0,20,0);
        valueParams.gravity = Gravity.CENTER_VERTICAL;
        imgArrowRight.setLayoutParams(valueParams);
        imgArrowRight.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgArrowRight.setAdjustViewBounds(true);
        imgArrowRight.setImageDrawable(this.activity.getDrawable(R.drawable.ic_angle_right));
        valueContainer.addView(imgArrowRight);
        return fieldContainer;
    }

    protected void inflateLookupView(ViewGroup valueContainer) {
        LinearLayout.LayoutParams valueParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        View valueView = activity.getLayoutInflater().inflate(value_layout, null);
        textName = valueView.findViewById(R.id.txt_customer_name);
        valueView.setLayoutParams(valueParams);
        valueContainer.addView(valueView);
    }

    private void lookupDocument(String documentName,  int requestCode) {
        Intent intent = new Intent(this.activity, BsDocLookUpActivity.class);
        intent.putExtra(DocumentName.DOCUMENT_NAME, documentName);
        activity.startActivityForResult(intent, requestCode);
    }

    public void setLookupValue(DocumentSnapshot doc) {
        if (!doc.getDataValue(documentName.toLowerCase(Locale.ROOT)).isNullValue("name")) {
            String name = doc.getDataValue(documentName.toLowerCase(Locale.ROOT)).getDataValue("name").getValue().toString();
            textName.setText(name);
        }
    }

}
