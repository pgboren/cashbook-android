package com.soleap.cashbook.common.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;

public class TextListItemViewHolder extends BaseViewHolder<String> {
    public TextListItemViewHolder(@NonNull View itemView, OnRecyclerViewListner listner) {
        super(itemView, listner);
    }

    @Override
    public void bind(String text, int position) {
        TextView textView = itemView.findViewById(R.id.txt_text);
        textView.setText(text.toString() + " ឆ្នាំ");
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onItemClicked(text, position);
            }
        });
    }
}
