package com.soleap.cashbook.common.activity;

import android.view.View;

import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.viewholder.BsDocViewHolder;

public class BsDocListActivity extends RecyclerActivity {

    private static final String TAG = "BsDocListActivity";

    @Override
    protected void bindListItemViewHolder(View itemView, int position, DocumentSnapshot doc) {
        BsDocViewHolder viewHolder = new BsDocViewHolder(this, documentName);
        viewHolder.bind(itemView, position, doc);
    }

}