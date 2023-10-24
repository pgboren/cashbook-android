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
import com.soleap.cashbook.view.ViewType;

public class InstituteListItemViewHolder extends DocListItemViewHolder {

    public InstituteListItemViewHolder(Context context, View itemView) {
        super(context, itemView, ViewType.LIST_ITEM_VIEW, DocumentName.INSTITUE, R.layout.list_item_name_view);
    }

    @Override
    protected void bindViewContent(Document document) {
        DocumentSnapshot doc = (DocumentSnapshot) document;
        String name = doc.getDataValue("name").getValue().toString();
        String latinName = doc.getDataValue("latinname").getValue().toString();
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_name).setString(name);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_latin_name).setString(latinName);
        ImageView imgLogo = itemView.findViewById(R.id.img_logo);

        if (doc.getDataValue("logo").getValue() != null) {
            MedialUtils.loadImage(context, doc.getDataValue("logo").getValue().toString(), imgLogo);
        }

    }

}
