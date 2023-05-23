package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.view.ViewType;

public class NameListItemViewHolder extends DocListItemViewHolder {

    public NameListItemViewHolder(Context context, View itemView) {
        super(context, itemView, ViewType.LIST_ITEM_VIEW, DocumentName.CONTACT, R.layout.list_item_contact);
    }

    @Override
    public void bind(int position, Document doc) {
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        String id = doc.getId();
        TextView txtIndex = itemView.findViewById(R.id.txt_index);
        txtIndex.setText(String.valueOf(position + 1));
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_id).setString((id));

        TextView circleBox = itemView.findViewById(R.id.txt_short_name);
        circleBox.setVisibility(View.VISIBLE);
        circleBox.setText(id.substring(0, 1).toUpperCase());
    }

}
