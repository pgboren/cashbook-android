package com.soleap.cashbook.activity;

import android.net.Uri;
import android.print.PrintDocumentInfo;
import android.telephony.PhoneNumberUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.IntentUtil;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.common.value.ViewType;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.widget.CircleImageView;

import java.util.Locale;

public class DealViewActivity extends ViewDataActivity<DocumentSnapshot> {

    private static final String TAG = "DealViewActivity";

}