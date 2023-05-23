package com.soleap.cashbook.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.adapter.RecyclerViewAdapter;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;

public class BsDocLookUpActivity extends RecyclerActivity implements RecyclerViewAdapter.EventListner {

    private static final String TAG = "BsDocLookUpActivity";
    public static final int LOOK_UP_CATEGORY_REQUEST_CODE = 10001;
    public static final int LOOK_UP_ITEM_REQUEST_CODE = 10002;
    public static final int LOOK_UP_INSTITUTE_REQUEST_CODE = 10003;
    public static final int LOOK_UP_COLOR_REQUEST_CODE = 10004;
    public static final int LOOK_UP_BRANCH_REQUEST_CODE = 10005;
    public static final int LOOK_UP_CONTACT_REQUEST_CODE = 10006;
    public static final String LOOK_UP_DOCUMENT = "LOOK_UP_DOCUMENT";

    public static final String DOC = "DOC";
    public static final String DOC_ID = "DOC_ID";
    public static final String DOC_TEXT = "DOC_TEXT";
    public static final String DOC_PHOTO = "DOC_PHOTO";
    public static final String DOC_CLASS = "DOC_CLASS";
    public static final String SHOW_PHOTO = "SHOW_PHOTO";

    private boolean showPhoto = false;

    @Override
    protected void onCreatingBegin() {
        super.onCreatingBegin();
        this.showPhoto = getIntent().getExtras().getBoolean(SHOW_PHOTO);
    }

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
    protected void initRecyclerViewAdapter() {
        super.initRecyclerViewAdapter();
        this.adapter.setListner(this);
    }

    @Override
    protected void onDocItemSelected(DocumentSnapshot doc) {
        ViewData data = doc.getDataValue("root").getDataValue("general");
        Intent returnIntent = new Intent();
        returnIntent.putExtra(DOC_ID, doc.getId());
        returnIntent.putExtra(DOC_TEXT, doc.getTitle());
        returnIntent.putExtra(DOC, doc);
        returnIntent.putExtra(DOC_CLASS, documentName);
        if  (showPhoto) {
            if (data.getDataValue("photo") !=null && data.getDataValue("photo").getValue() != null) {
                returnIntent.putExtra(DOC_PHOTO, data.getDataValue("photo").getValue().toString());
            }
        }
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void onItemSelected(DocumentSnapshot documentSnapshot) {
        onDocItemSelected(documentSnapshot);
    }

    @Override
    public void onStartListening() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onStopListening() {

    }
}