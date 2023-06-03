package com.soleap.cashbook.common.widget.docitems;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.document.Document;

public abstract class DocItemsViewHolder<D extends  Document> extends RecyclerView.ViewHolder {

    public DocItemsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    protected abstract void bind(D item);
}
