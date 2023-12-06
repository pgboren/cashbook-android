package com.soleap.cashbook.widget.refdoc;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.RefDocument;

public class RefDocItemViewHolder extends RecyclerView.ViewHolder {
    private RefDocAdapter.EventListner listner;

    private RefDocument  refDocument;
    public RefDocItemViewHolder(@NonNull View itemView, RefDocAdapter.EventListner listner) {
        super(itemView);
        this.listner = listner;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onItemClick(refDocument, getAdapterPosition());
            }
        });
    }

    public void bind(RefDocument refDocument) {
        this.refDocument = refDocument;
        TextView txtView = itemView.findViewById(R.id.txt_name);
        txtView.setText(refDocument.getName());
    }
}
