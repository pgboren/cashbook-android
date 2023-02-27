package com.soleap.cashbook.common.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocLookUpActivity;
import com.soleap.cashbook.common.util.MedialUtils;
import com.soleap.cashbook.document.DocumentInfo;

public class DocLookUpInputView extends LinearLayout {

    private String value;
    private String docName;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    private TextView textTitle;
    private TextView textValue;
    private View imgPhotoViewContainer;
    private ImageView imageView;
    private TextView txtAbriviationText;

    public DocLookUpInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DocLookUpInputView, 0, 0);
        String title = a.getString(R.styleable.DocLookUpInputView_title);
        String blankMessage = a.getString(R.styleable.DocLookUpInputView_blank_message);
        docName = a.getString(R.styleable.DocLookUpInputView_documentName);
        int documentPhotoVisible = a.getInt(R.styleable.DocLookUpInputView_documentPhotoVisible, 0);
        boolean titleVisible = a.getBoolean(R.styleable.DocLookUpInputView_title_visible, true);
        Drawable endIconDrawable = a.getDrawable(R.styleable.DocLookUpInputView_endIconDrawable);
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.document_lookup_view, this, true);
        TextView textTitle = findViewById(R.id.document_title);
        textTitle.setText(title);
        if (!titleVisible) {
            textTitle.setVisibility(GONE);
        }

        TextView txtPromptMsg = findViewById(R.id.txt_prompt_msg);
        txtPromptMsg.setText(blankMessage);

        imgPhotoViewContainer = findViewById(R.id.img_photo_container);
        imageView = findViewById(R.id.img_photo);
        txtAbriviationText = findViewById(R.id.circle_box);

        textValue = findViewById(R.id.txt_text);


        View endIconLayout = findViewById(R.id.btn_end_icon);
        ImageView imgVEndIcon = findViewById(R.id.imv_end_icon);
        if (endIconDrawable != null) {
            imgVEndIcon.setImageDrawable(endIconDrawable);
            endIconLayout.setVisibility(View.VISIBLE);
        }
        else {
            endIconLayout.setVisibility(View.GONE);
        }
        endIconLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BsDocLookUpActivity.class);
                intent.putExtra(DocumentInfo.DOCUMENT_NAME, docName);
                intent.putExtra(BsDocLookUpActivity.SHOW_PHOTO, documentPhotoVisible != 8);
                activityResultLauncher.launch(intent);
            }
        });

        AppCompatActivity activity = (AppCompatActivity) context;
        activityResultLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            setValue(data.getStringExtra(BsDocLookUpActivity.DOC_ID));
                            textValue.setText(data.getStringExtra(BsDocLookUpActivity.DOC_TEXT));
                            findViewById(R.id.value_view_container).setVisibility(View.VISIBLE);
                            findViewById(R.id.blank_view_container).setVisibility(View.GONE);
                            if (documentPhotoVisible != 8) {
                                String path = data.getStringExtra(BsDocLookUpActivity.DOC_PHOTO);
                                if (path != null) {
                                    MedialUtils.loadImage(context, path, imageView);
                                    imageView.setVisibility(VISIBLE);
                                    txtAbriviationText.setVisibility(GONE);
                                }
                                else {

                                    txtAbriviationText.setVisibility(VISIBLE);
                                    imageView.setVisibility(GONE);
                                }
                            }
                            else {
                                txtAbriviationText.setVisibility(VISIBLE);
                                imageView.setVisibility(GONE);
                            }
                        }


                    }
                }
        );

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        textValue.setText(value);
    }
}
