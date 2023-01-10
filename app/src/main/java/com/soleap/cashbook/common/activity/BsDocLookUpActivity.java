package com.soleap.cashbook.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.common.util.ColorUtils;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.common.value.ViewType;

import java.util.Locale;
import java.util.Random;

public class BsDocLookUpActivity extends RecyclerActivity {

    private static final String TAG = "BsDocLookUpActivity";
    public static final int LOOK_UP_CATEGORY_REQUEST_CODE = 10001;
    public static final int LOOK_UP_ITEM_REQUEST_CODE = 10002;
    public static final int LOOK_UP_INSTITUTE_REQUEST_CODE = 10003;
    public static final int LOOK_UP_COLOR_REQUEST_CODE = 10004;
    public static final String LOOK_UP_DOCUMENT = "LOOK_UP_DOCUMENT";

    @Override
    protected void setViewContent() {
        super.setViewContent();
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_close);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            setSupportActionBar(toolbar);
        }
    }

    @Override
    protected void bindListItemViewHolder(View itemView, int position, DocumentSnapshot doc) {
        if (documentName.equals(DocumentInfo.ITEM)) {
            bindItemViewHolder(itemView, position, doc);
        }
        else {
            bindBSDocuItemViewHolder(itemView, position, doc);
        }
    }

    private void bindBSDocuItemViewHolder(View itemView, int position, DocumentSnapshot doc) {
        ViewData data = doc.getDataValue(documentName);
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        String name = data.getDataValue("name").getValue().toString();
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_name).setString((name));
        TextView shortcut = itemView.findViewById(R.id.shortcut);
        TextView circleBox = itemView.findViewById(R.id.circle_box);
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);

        if (documentName.equals(DocumentInfo.COLOR)) {
            shape.setColor(Color.parseColor (data.getDataValue("code").getValue().toString()));
        }
        else {
            shape.setColor(Color.parseColor ("#"+ ColorUtils.colors[new Random().nextInt(254)]));
        }

        circleBox.setVisibility(View.VISIBLE);
        circleBox.setBackground(shape);
        circleBox.setText(name.substring(0, 1).toUpperCase());
        shortcut.setText(String.valueOf(position + 1));


    }

    private void bindItemViewHolder(View itemView, int position, DocumentSnapshot doc) {
        ViewData data = doc.getDataValue(documentName);
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_name).setString((data.getDataValue("name").getValue().toString()));
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_name_kh).setString((data.getDataValue("nameKh").getValue().toString()));
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_category).setString((data.getDataValue("category").getValue().toString()));
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_full_price).setCurrency(Double.parseDouble(data.getDataValue("price").getValue().toString()), Locale.US);
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_isntallment_price).setCurrency(Double.parseDouble(data.getDataValue("installmentPaymentPrice").getValue().toString()), Locale.US);
        TextView circleBox = itemView.findViewById(R.id.circle_box);
        TextView shortcut = itemView.findViewById(R.id.shortcut);
        ImageView imageView = (ImageView)itemView.findViewById(R.id.imv_item_photo);
        if (data.getDataValue("photo").getValue() != null) {
            imageView.setVisibility(View.VISIBLE);
            circleBox.setVisibility(View.GONE);
            loadImage(APIClient.STATIC_URL + data.getDataValue("photo").getValue().toString(), imageView);
        }
        else {
            imageView.setVisibility(View.GONE);
            circleBox.setVisibility(View.VISIBLE);
        }
        shortcut.setText(String.valueOf(position + 1));
    }

    void loadImage(String path, ImageView imageView) {
        if (path != null) {
            Uri uri = Uri.parse(path);
            Glide.with(BsDocLookUpActivity.this)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .skipMemoryCache(true)
                    .into(imageView);
        }
    }

    @Override
    protected void onDocItemSelected(DocumentSnapshot doc) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(LOOK_UP_DOCUMENT, doc);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}