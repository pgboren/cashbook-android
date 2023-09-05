package com.soleap.cashbook.common.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;

public class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected final OnRecyclerViewListner listner;

    public BaseViewHolder(@NonNull View itemView, OnRecyclerViewListner listner) {
        super(itemView);
        this.listner = listner;
    }

    public void bind(T text, int position) {
        TextView textView = itemView.findViewById(R.id.txt_text);
        textView.setText(text.toString());
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onItemClicked(text, position);
            }
        });
    }
}
