package com.soleap.cashbook.viewholder;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.ColorUtils;
import com.soleap.cashbook.common.util.MedialUtils;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.common.value.ViewType;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.restapi.APIClient;

import java.util.Locale;
import java.util.Random;

public class BsDocViewHolder {

    private String documentName;
    private Activity activity;

    public BsDocViewHolder(Activity activity, String documentName) {
        this.documentName = documentName;
        this.activity = activity;
    }

    public void bind(View itemView, final int position, final DocumentSnapshot doc) {

        if (documentName.equals(DocumentInfo.ITEM)) {
            bindItem(itemView, position, doc);
            return;
        }

        if (documentName.equals(DocumentInfo.CONTACT)) {
            bindContact(itemView, position, doc);
            return;
        }

        if (documentName.equals(DocumentInfo.INSTITUE)) {
            bindInsitituteItem(itemView, position, doc);
            return;
        }

        if (documentName.equals(DocumentInfo.SALE)) {
            bindDealItem(itemView, position, doc);
            return;
        }

        bindBsDocu(itemView, position, doc);

    }

    protected void bindDealItem(View itemView, int position, DocumentSnapshot doc) {
        ViewData data = doc.getDataValue("deal");
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        TextView txtCustomer = itemView.findViewById(R.id.txt_deal_customer);
        TextView txtDealNumber = itemView.findViewById(R.id.txt_deal_number);
        TextView txtDate = itemView.findViewById(R.id.txt_deal_date);
        TextView txtPrice = itemView.findViewById(R.id.txt_deal_price);
        ViewData customer = data.getDataValue("customer");
        txtCustomer.setText(customer.getDataValue("lastname").getValue().toString() + " " + customer.getDataValue("firstname").getValue().toString());
        txtDealNumber.setText(data.getDataValue("number").getValue().toString());
        txtDate.setText(data.getDataValue("date").getValue().toString());
        double price = (double)data.getDataValue("price").getValue();
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_deal_price).setCurrency(price, Locale.US);

        ImageView imgView = itemView.findViewById(R.id.img_deal_customer_photo);
        MedialUtils.loadImage(this.activity, data.getDataValue("customer").getDataValue("photo").getValue().toString(), imgView);
    }

    private void bindInsitituteItem(View itemView, int position, DocumentSnapshot doc) {
        ViewData data = doc.getDataValue("root").getDataValue("general");
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        String name = data.getDataValue("name").getValue().toString();
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_name).setString((name));
        TextView shortcut = itemView.findViewById(R.id.shortcut);
        TextView circleBox = itemView.findViewById(R.id.circle_box);

        ImageView imageView = (ImageView)itemView.findViewById(R.id.imv_item_photo);
        if (data.getDataValue("photo").getValue() != null) {
            imageView.setVisibility(View.VISIBLE);
            circleBox.setVisibility(View.GONE);
            MedialUtils.loadImage(activity, data.getDataValue("photo").getValue().toString(), imageView);
        }
        else {
            imageView.setVisibility(View.GONE);
            circleBox.setVisibility(View.VISIBLE);
        }

        circleBox.setText(name.substring(0, 1).toUpperCase());
        shortcut.setText(name.substring(0, 1).toUpperCase());
    }

    private void bindContact(View itemView, int position, DocumentSnapshot doc) {
        ViewData data = doc.getDataValue("root").getDataValue("general");
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        String name = data.getDataValue("name").getValue().toString();
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_name).setString((name));
        TextView shortcut = itemView.findViewById(R.id.shortcut);
        TextView circleBox = itemView.findViewById(R.id.circle_box);

        ImageView imageView = (ImageView)itemView.findViewById(R.id.imv_item_photo);
        if (data.getDataValue("photo").getValue() != null) {
            imageView.setVisibility(View.VISIBLE);
            circleBox.setVisibility(View.GONE);
            MedialUtils.loadImage(activity, data.getDataValue("photo").getValue().toString(), imageView);
        }
        else {
            imageView.setVisibility(View.GONE);
            circleBox.setVisibility(View.VISIBLE);
        }

        circleBox.setText(name.substring(0, 1).toUpperCase());
        shortcut.setText(name.substring(0, 1).toUpperCase());
    }

    private void bindBsDocu(View itemView, int position, DocumentSnapshot doc) {
        ViewData data = doc.getDataValue("root").getDataValue("general");
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

    void bindItem(View itemView, final int position, final DocumentSnapshot doc) {
        ViewData data = doc.getDataValue("root").getDataValue("general");
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
            MedialUtils.loadImage(activity, data.getDataValue("photo").getValue().toString(), imageView);
        }
        else {
            imageView.setVisibility(View.GONE);
            circleBox.setVisibility(View.VISIBLE);
        }
        shortcut.setText(String.valueOf(position + 1));
    }
}
