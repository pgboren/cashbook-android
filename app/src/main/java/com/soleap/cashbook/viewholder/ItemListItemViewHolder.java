package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.util.MedialUtils;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.view.ViewType;

import java.util.Locale;

public class ItemListItemViewHolder extends DocListItemViewHolder {

    public ItemListItemViewHolder(Context context, View itemView) {
        super(context, itemView, ViewType.LIST_ITEM_VIEW, DocumentName.CONTACT, R.layout.vehicle_list_item_view);
    }

    @Override
    protected void bindViewContent(Document document) {
        DocumentSnapshot doc = (DocumentSnapshot) document;
        String name = doc.getDataValue("name").getValue().toString();
        String category = doc.getDataValue("category").getValue().toString();
        String account = doc.getDataValue("account").getValue().toString();
        double price = (Double) doc.getDataValue("price").getValue();
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_name).setString(name);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_price).setCurrency(price, Locale.US);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_category).setString(category);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_account).setString(account);
        if (doc.getDataValue("photo").getValue() != null) {
            MedialUtils.loadImage(itemView.getContext(), doc.getDataValue("photo").getValue().toString(), itemView.findViewById(R.id.photo_view));
            itemView.findViewById(R.id.short_name_view).setVisibility(View.GONE);
            itemView.findViewById(R.id.photo_view).setVisibility(View.VISIBLE);
        }
        else {
            viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.short_name_view).setString((name.substring(0, 1).toUpperCase()));
            itemView.findViewById(R.id.photo_view).setVisibility(View.GONE);
            itemView.findViewById(R.id.short_name_view).setVisibility(View.VISIBLE);
        }
    }

}
