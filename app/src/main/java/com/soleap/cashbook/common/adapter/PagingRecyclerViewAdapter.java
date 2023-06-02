package com.soleap.cashbook.common.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.PagingRecyclerViewData;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;
import com.soleap.cashbook.viewholder.ListItemViewHolder;
import com.soleap.cashbook.viewholder.ListItemViewHolderFactory;

import java.util.Map;

public class PagingRecyclerViewAdapter extends RecyclerViewAdapter implements DocumentSnapshotRepository.OnGetPagingDocsRequestListner {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private int currentPage = 1;
    private int maxPage = -1;

    private boolean isFirstBind = true;

    private PagingRecyclerViewAdapterDataListner dataListner;

    public void setDataListner(PagingRecyclerViewAdapterDataListner dataListner) {
        this.dataListner = dataListner;
    }

    public PagingRecyclerViewAdapter(Context context, String documentName, int viewResource) {
        super(context, documentName, viewResource);
    }

    @NonNull
    @Override
    public DocListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(itemView.getLayout(), parent, false);
            this.viewHolder = ListItemViewHolderFactory.create(context, view, itemView.getDocName());
            return this.viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_name_shimmer_view, parent, false);
            this.viewHolder = new PagingListItemLoadingViewHolder(parent.getContext(), view);
        }
        return  this.viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final DocumentSnapshot documentSnapshot = (DocumentSnapshot) dataSet.get(position);
        if (documentSnapshot != null) {
            DocListItemViewHolder viewHolder = (DocListItemViewHolder) holder;
            viewHolder.bind(position, documentSnapshot);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    private void showLoadingView(PagingListItemLoadingViewHolder viewHolder, int position) {

    }

    @Override
    public void onAdded(Document documentSnapshot) {
        this.dataSet.add(0, documentSnapshot);
        notifyDataSetChanged();
    }


    @Override
    protected void initRepository() {
        this.repository = RepositoryFactory.create().get(documentName);
        this.repository.setPagingDocsRequestListner(this);
    }

    boolean isLoading = false;

    public void moveNextPage() {

        if (currentPage < maxPage && !isLoading ) {
            this.currentPage +=1;
            isLoading = true;
            dataSet.add(null);
            notifyItemInserted(dataSet.size() - 1);
            loadPage(this.currentPage);
        }
    }

    private Handler handler = new Handler();

    private Runnable runnable;


    public void loadPage(int page) {

        if (runnable != null) {
            handler.removeCallbacks(runnable);
            isLoading = true;
        }
        if (listner != null) {
            PagingRecyclerViewAdapter.this.listner.onStartListening();
        }
        if (page == 1) {
            dataSet.clear();
            notifyDataSetChanged();
            dataSet.add(null);
            notifyItemInserted(dataSet.size() - 1);
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                repository.list(page);
            }
        };

        new Handler().postDelayed(runnable, 2000);
    }

    @Override
    public void startListening() {
    }

    @Override
    protected DocListItemViewHolder createItemViewHolder(View view) {
        return null;
    }

    @Override
    public void onGet(PagingRecyclerViewData pagingData) {

        try
        {
            if (dataSet.size() > 0) {
                dataSet.remove(dataSet.size()-1);
                notifyItemRemoved(dataSet.size());
            }

            this.dataSet.addAll(pagingData.getData());
            maxPage = pagingData.getTotalPages();
            this.notifyDataSetChanged();
            isLoading = false;
            if (this.listner != null) {
                this.listner.onStopListening();
            }

        }
        catch (Exception ex) {
            Log.e("ERROR", ex.getMessage(), ex);
        }
    }

    private class PagingListItemLoadingViewHolder extends DocListItemViewHolder {

        private ProgressBar progressBar;

        public PagingListItemLoadingViewHolder(Context context, @NonNull View itemView) {
            super(context, itemView, null, null, 0);
        }


        @Override
        protected void bindViewContent(DocumentSnapshot doc) {

        }
    }

    public interface PagingRecyclerViewAdapterDataListner {
        void onRequest();
        void onResponse();

        void onError();
    }
}
