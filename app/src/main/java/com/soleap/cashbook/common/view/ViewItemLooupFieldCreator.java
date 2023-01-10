package com.soleap.cashbook.common.view;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocLookUpActivity;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.restapi.APIClient;

public class ViewItemLooupFieldCreator extends ViewDocumentLooupFieldCreator {

    private ImageView imgPhoto;
    private TextView textNameKh;
    private View circleBox;
    private DocumentSnapshot selectedDoc = null;

    public void setSelectedDoc(DocumentSnapshot doc) {
        this.selectedDoc = doc;
        setSelectedDoc(this.selectedDoc);
    }

    public DocumentSnapshot getSelectedDoc() {
        return this.selectedDoc;
    }

    public ViewItemLooupFieldCreator(ViewDataActivity activity, ViewData fieldData) {
        super(activity, DocumentInfo.ITEM, BsDocLookUpActivity.LOOK_UP_ITEM_REQUEST_CODE, fieldData, R.layout.lookup_item_item);
    }

    @Override
    protected void inflateLookupView(ViewGroup valueContainer) {
        super.inflateLookupView(valueContainer);
        imgPhoto = valueContainer.findViewById(R.id.imv_item_photo);
        textNameKh = valueContainer.findViewById(R.id.txt_name_kh);
        circleBox = valueContainer.findViewById(R.id.circle_box);
    }

    @Override
    public void setLookupValue(DocumentSnapshot doc) {
        super.setLookupValue(doc);
        if (!doc.getDataValue(DocumentInfo.ITEM).isNullValue("photo")) {
            String path = doc.getDataValue(DocumentInfo.ITEM).getDataValue("photo").getValue().toString();
            loadImage(path, imgPhoto);
            circleBox.setVisibility(View.GONE);
            imgPhoto.setVisibility(View.VISIBLE);
        }
        else {
            circleBox.setVisibility(View.VISIBLE);
            imgPhoto.setVisibility(View.GONE);
        }
        textNameKh.setText(doc.getDataValue(DocumentInfo.ITEM).getDataValue("nameKh").getValue().toString());
        textName.setText(doc.getDataValue(DocumentInfo.ITEM).getDataValue("name").getValue().toString());
    }

    private void loadImage(String path, ImageView imageView) {
        if (path != null) {
            Uri uri = Uri.parse(APIClient.STATIC_URL + path);
            Glide.with(this.activity)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .skipMemoryCache(true)
                    .into(imageView);
        }
    }
}
