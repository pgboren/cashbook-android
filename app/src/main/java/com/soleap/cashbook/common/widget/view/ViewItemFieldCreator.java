package com.soleap.cashbook.common.widget.view;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.MedialUtils;
import com.soleap.cashbook.common.util.ResourceUtil;

public class ViewItemFieldCreator extends FieldCreator {

    public ViewItemFieldCreator(ViewDataActivity context, ViewData data) {
        super(context, data);
    }

    @Override
    public View createView() {
        ViewData data = fieldData;
        LinearLayout valueContainer = new LinearLayout(activity);
        valueContainer.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 15,0,15);
        valueContainer.setLayoutParams(params);
        valueContainer.setOrientation(LinearLayout.HORIZONTAL);
        TextView textLabel = new TextView(activity);
        textLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.4f));
        textLabel.setTextColor(activity.getColor(R.color.secondaryTextColor));
        textLabel.setText(ResourceUtil.getStringResourceByName(activity, data.getLabel().toLowerCase()));
        valueContainer.addView(textLabel);

        ShapeableImageView imageView = new ShapeableImageView(activity);
        int size = (int) activity.getResources().getDimension(R.dimen.view_photo_field_size);
        ShapeAppearanceModel shapeAppearanceModel = imageView.getShapeAppearanceModel().toBuilder().setAllCornerSizes(size/2).build();
        imageView.setShapeAppearanceModel(shapeAppearanceModel);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        params =new LinearLayout.LayoutParams(size, size);
        params.gravity = Gravity.CENTER;
        imageView.setLayoutParams(params);
        valueContainer.addView(imageView);

        if (data.getDataValue("photo").getValue() != null) {
            MedialUtils.loadImage(activity, data.getDataValue("photo").getValue().toString(), imageView);
        }
        else {
            imageView.setImageDrawable(activity.getDrawable(R.drawable.ic_photo));
        }

        TextView textName = new TextView(activity);

        params = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.WRAP_CONTENT, 0.4f);
        params.setMargins(20, 20,0,20);
        textName.setLayoutParams(params);
        textName.setTextColor(activity.getColor(R.color.secondaryTextColor));
        textName.setText( data.getDataValue("name").getValue().toString());
        valueContainer.addView(textName);

        return valueContainer;
    }

}
