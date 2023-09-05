package com.soleap.cashbook.common.widget.lookup;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.TextEnumValueDocument;
import com.soleap.cashbook.common.viewholder.BaseViewHolder;
import com.soleap.cashbook.common.viewholder.OnRecyclerViewListner;

public class TextEnumListItemViewHolder extends BaseViewHolder<TextEnumValueDocument> {
    public TextEnumListItemViewHolder(@NonNull View itemView, OnRecyclerViewListner listner) {
        super(itemView, listner);
    }

    @Override
    public void bind(TextEnumValueDocument doc, int position) {
        TextView textView = itemView.findViewById(R.id.txt_text);
        textView.setText(doc.toString());
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onItemClicked(doc, position);
            }
        });
    }
}
