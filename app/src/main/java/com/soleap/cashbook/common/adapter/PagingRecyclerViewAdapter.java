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

import com.soleap.cashbook.Global;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.PagingRecyclerViewData;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.repository.AgileTaskRepository;
import com.soleap.cashbook.viewholder.BsDocViewHolder;

public class PagingRecyclerViewAdapter extends RecyclerViewAdapter implements DocumentSnapshotRepository.OnGetPagingDocsRequestListner {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private int currentPage = 1;
    private int maxPage = -1;
    private String[] stages;
    private String board = Global.agile_board;

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            return super.onCreateViewHolder(parent, viewType);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agile_task_list_item_shimmer_loading, parent, false);
            return new PagingListItemLoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (viewHolder instanceof PagingListItemLoadingViewHolder) {
            showLoadingView((PagingListItemLoadingViewHolder) viewHolder, position);
        }
        else {
            super.onBindViewHolder((ViewHolder) holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    private void showLoadingView(PagingListItemLoadingViewHolder viewHolder, int position) {

    }

    @Override
    protected ViewHolder createItemViewHolder(View itemView) {
        return new ViewHolder(itemView) {
            @Override
            protected void bind( int position, DocumentSnapshot data) {
                bindListItemViewHolder(this.itemView, position, data);
            }
        };
    }

    protected void bindListItemViewHolder(View itemView, int position, DocumentSnapshot doc) {
        BsDocViewHolder viewHolder = new BsDocViewHolder((Activity) context, DocumentInfo.AGILE_TASK);
        viewHolder.bind(itemView, position, doc);
    }

    @Override
    public void onAdded(DocumentSnapshot documentSnapshot) {
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
            loadPage(board, this.stages, this.currentPage);
        }
    }

    private Handler handler = new Handler();

    private Runnable runnable;


    public void loadPage(String board, String[] stages, int page) {

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
                PagingRecyclerViewAdapter.this.stages = stages;
                AgileTaskRepository taskRepository = (AgileTaskRepository)repository;
                taskRepository.getTasks(board ,stages, page);
            }
        };

        new Handler().postDelayed(runnable, 2000);
    }

    @Override
    public void startListening() {
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

    private class PagingListItemLoadingViewHolder extends ViewHolder {

        private ProgressBar progressBar;

        public PagingListItemLoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position, DocumentSnapshot data) {
        }
    }

    public interface PagingRecyclerViewAdapterDataListner {
        void onRequest();
        void onResponse();

        void onError();
    }
}
