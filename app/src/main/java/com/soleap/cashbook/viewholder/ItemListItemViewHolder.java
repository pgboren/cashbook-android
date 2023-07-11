package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.view.ViewType;

import java.util.Locale;

public class ItemListItemViewHolder extends DocListItemViewHolder {

    public ItemListItemViewHolder(Context context, View itemView) {
        super(context, itemView, ViewType.LIST_ITEM_VIEW, DocumentName.CONTACT, R.layout.list_item_item_view);
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
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_short_name).setString((name.substring(0, 1).toUpperCase()));
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_price).setCurrency(price, Locale.US);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_category).setString(category);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_account).setString(account);
    }

}
