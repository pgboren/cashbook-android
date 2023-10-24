package com.soleap.cashbook.common.widget.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputEditText;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Media;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.MedialUtils;
import com.soleap.cashbook.common.widget.BaseTextInputView;
import com.soleap.cashbook.restapi.APIClient;

import java.util.ArrayList;

public class PhotoView extends BaseView {

    private ShapeableImageView imageView;

    private Media media;

    private String medialId = null;

    public void setMedialId(String medialId) {
        this.medialId = medialId;
    }

    private void loadMediaFromServer() {

    }

    public PhotoView(Context context) {
        super((Context)context);
        setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 60,0,60);
        setLayoutParams(params);
        setOrientation(LinearLayout.HORIZONTAL);
        int size = (int) context.getResources().getDimension(R.dimen.view_photo_field_size);

        imageView = new ShapeableImageView(context);
        ShapeAppearanceModel shapeAppearanceModel = imageView.getShapeAppearanceModel().toBuilder().setAllCornerSizes(size/2).build();
        imageView.setShapeAppearanceModel(shapeAppearanceModel);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageDrawable(imageView.getContext().getDrawable(R.drawable.ic_photo_group));
        params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 600);
        imageView.setLayoutParams(params);
        addView(imageView);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                    viewPhoto(media.getPath());
            }
        });

    }

    private void viewPhoto(String path) {
        Uri uri = Uri.parse(APIClient.STATIC_URL + path);
        final ArrayList<String> uriString = new ArrayList<>();
        uriString.add(uri.toString());
        Intent fullImageIntent = new Intent(getContext(), FullScreenImageViewActivity.class);
        fullImageIntent.putExtra(FullScreenImageViewActivity.URI_LIST_DATA, uriString);
        fullImageIntent.putExtra(FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS, 0);
        getContext().startActivity(fullImageIntent);
    }
}
