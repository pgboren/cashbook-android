package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.util.MedialUtils;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.document.Institute;
import com.soleap.cashbook.view.ViewType;

public class InstituteListItemViewHolder extends DocListItemViewHolder {

    public InstituteListItemViewHolder(Context context, View itemView) {
        super(context, itemView, ViewType.LIST_ITEM_VIEW, DocumentName.INSTITUE, R.layout.list_item_name_view);
    }

    @Override
    protected void bindViewContent(Document document) {
        Institute doc = (Institute) document;
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_name).setString(doc.getName());
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_latin_name).setString(doc.getLatinname());
        ImageView imgLogo = itemView.findViewById(R.id.img_logo);
        if (doc.getLogoMedia() != null) {
            MedialUtils.loadImage(context, doc.getLogoMedia().getPath(), imgLogo);
        }

    }

}
