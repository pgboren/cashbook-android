package com.soleap.cashbook.activity;

import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.common.util.IntentUtil;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.common.value.ViewType;
import com.soleap.cashbook.widget.CircleImageView;

import java.util.Locale;

public class ContactViewActivity extends ViewDataActivity<DocumentSnapshot> {

    private static final String TAG = "ContactViewActivity";
    public final static int PICK_IMAGE_REQUEST_CODE = 104;

    protected Class editActivityClass() {
        return ContactEditActivity.class;
    }
//
//    @Override
//    protected void renderViewData(DocumentSnapshot doc) {
//
//        LinearLayout rootView = findViewById(R.id.content_container);
//        rootView.removeAllViews();
//        LayoutInflater li = LayoutInflater.from(this);
//        View contactView = li.inflate(R.layout.contact_view, null);
//        rootView.addView(contactView);
//
//        ViewData contactDoc = doc.getDataValue("contact");
//        if (!contactDoc.isNullValue("photo")) {
//            CircleImageView imageView = findViewById(R.id.contact_img);
//            String path = doc.getDataValue("contact").getDataValue("photo").getValue().toString();
//            Uri uri = Uri.parse(APIClient.STATIC_URL + path);
//            Glide.with(this)
//                    .load(uri)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
//                    .into(imageView);
//        }
//
//        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(rootView);
//
//        String name = contactDoc.getDataValue("lastname").getValue().toString() + " " + contactDoc.getDataValue("firstname").getValue().toString();
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_name).setString(name);
//
//        final String phonenumber = contactDoc.getDataValue("phoneNumber1").getValue().toString();
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_primary_mobile).setString(PhoneNumberUtils.formatNumber(phonenumber, Locale.getDefault().getCountry()));
//
//        ViewData addressData = contactDoc.getDataValue("address");
//        String village = addressData.getDataValue("village").getValue().toString();
//        String commune = addressData.getDataValue("commune").getValue().toString();
//        String district = addressData.getDataValue("district").getValue().toString();
//        String province = addressData.getDataValue("province").getValue().toString();
//
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_village).setString(village);
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_commune).setString(commune);
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_distric).setString(district);
//        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_province).setString(province);
//
//        if (!contactDoc.isNullValue("phoneNumber1")) {
//            final String phonenumber1 = contactDoc.getDataValue("phoneNumber1").getValue().toString();
//            viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_phoneNumber1).setString(phonenumber1);
//            rootView.findViewById(R.id.layout_phoneNumber1).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    intentPhonenumber(phonenumber1);
//                }
//            });
//        }
//        else {
//            findViewById(R.id.layout_phoneNumber1).setVisibility(View.GONE);
//        }
//
//        if (!contactDoc.isNullValue("phoneNumber2")) {
//            final String phoneNumber2 = contactDoc.getDataValue("phoneNumber2").getValue().toString();
//            viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_phoneNumber2).setString(phoneNumber2);
//            rootView.findViewById(R.id.layout_phoneNumber2).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    intentPhonenumber(phoneNumber2);
//                }
//            });
//        }
//        else {
//            findViewById(R.id.layout_phoneNumber2).setVisibility(View.GONE);
//        }
//
//        if (!contactDoc.isNullValue("phoneNumber3")) {
//            final String phoneNumber3 = contactDoc.getDataValue("phoneNumber3").getValue().toString();
//            viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_phoneNumber3).setString(phoneNumber3);
//            rootView.findViewById(R.id.layout_phoneNumber3).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    intentPhonenumber(phoneNumber3);
//                }
//            });
//        }
//        else {
//            findViewById(R.id.layout_phoneNumber3).setVisibility(View.GONE);
//        }
//
//        rootView.findViewById(R.id.txt_primary_mobile).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intentPhonenumber(phonenumber);
//            }
//        });
//
//        imageView = findViewById(R.id.contact_img);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pickImage();
//            }
//        });
//    }

     private void intentPhonenumber(String number) {
         IntentUtil.CallPhone(number, ContactViewActivity.this);
     }

}