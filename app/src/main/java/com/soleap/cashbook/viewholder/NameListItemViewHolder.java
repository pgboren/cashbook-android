package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.view.ViewType;

public class NameListItemViewHolder extends DocListItemViewHolder {

    public NameListItemViewHolder(Context context, View itemView) {
        super(context, itemView, ViewType.LIST_ITEM_VIEW, DocumentName.CONTACT, R.layout.list_item_name_view);
    }

    @Override
    protected void bindViewContent(DocumentSnapshot doc) {
        String name = doc.getDataValue("name").getValue().toString();
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_name).setString(name);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_short_name).setString((name.substring(0, 1).toUpperCase()));
    }

}
