package com.soleap.cashbook.common.activity;

import android.os.Handler;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;

public class BsDocListActivity extends RecyclerActivity {

    private static final String TAG = "BsDocListActivity";

    public static final String READ_ONLY = "READ_ONLY";

    protected void startDataListening() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PagingRecyclerViewAdapter pagingRecyclerViewAdapter = (PagingRecyclerViewAdapter)adapter;
                pagingRecyclerViewAdapter.loadPage(1);
            }
        }, 500);
    }

    @Override
    protected void initRecyclerView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        initScrollListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean isReadOnly = getIntent().getExtras().getBoolean(READ_ONLY);
        if (!isReadOnly) {
            return super.onCreateOptionsMenu(menu);
        }
        return true;
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
                    ((PagingRecyclerViewAdapter)adapter).moveNextPage();
                }
            }
        });
    }
}