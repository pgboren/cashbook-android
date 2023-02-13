package com.soleap.cashbook.common.widget.recyclerview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.document.Document;

public abstract class SwipeToDeleteRecyclerViewHolder extends RecyclerView.ViewHolder {

    public SwipeToDeleteRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    protected abstract void bind(Document item);

}
