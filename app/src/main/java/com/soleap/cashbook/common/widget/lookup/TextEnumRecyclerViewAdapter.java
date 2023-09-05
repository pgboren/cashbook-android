package com.soleap.cashbook.common.widget.lookup;

import android.view.View;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.TextEnumValueDocument;
import com.soleap.cashbook.common.viewholder.OnRecyclerViewListner;
import com.soleap.cashbook.common.viewholder.TextListItemViewHolder;

public class TextEnumRecyclerViewAdapter extends BaseLookupRecyclerViewAdapter<TextEnumValueDocument, TextEnumListItemViewHolder> {
    @Override
    protected TextEnumListItemViewHolder createViewHolder(View itemView, OnRecyclerViewListner listner) {
        return new TextEnumListItemViewHolder(itemView, listner);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.text_enum_list_item_view;
    }

}
