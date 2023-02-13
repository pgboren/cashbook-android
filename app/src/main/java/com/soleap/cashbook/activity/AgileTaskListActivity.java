package com.soleap.cashbook.activity;

import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.RecyclerActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.viewholder.BsDocViewHolder;

public class AgileTaskListActivity extends RecyclerActivity {

    private static final String TAG = "BsDocListActivity";

    @Override
    protected void bindListItemViewHolder(View itemView, int position, DocumentSnapshot doc) {
        BsDocViewHolder viewHolder = new BsDocViewHolder(this, documentName);
        viewHolder.bind(itemView, position, doc);
    }

    @Override
    protected void initRecyclerView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}