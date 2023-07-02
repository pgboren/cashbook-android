package com.soleap.cashbook.common.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.PagingRecyclerViewData;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;
import com.soleap.cashbook.viewholder.ListItemViewHolderFactory;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagingRecyclerViewAdapter extends RecyclerViewAdapter {

    private Map<String, Object> filter;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private int currentPage = 1;
    protected int maxPage = -1;

    private boolean isFirstBind = true;
    protected boolean isLoading = false;

    protected Handler handler = new Handler();

    protected Runnable runnable;

    private PagingRecyclerViewAdaptaerEventListner listner;

    public void setListner(PagingRecyclerViewAdaptaerEventListner listner) {
        this.listner = listner;
    }

    public PagingRecyclerViewAdapter(Context context, String documentName, int viewResource) {
        super(context, documentName, viewResource);
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }


    @NonNull
    @Override
    public DocListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(documentInfo.getDocListViewDef().getList_item_layout(), parent, false);
            this.viewHolder = ListItemViewHolderFactory.create(context, view, documentInfo.getName());
            this.viewHolder.setListener(new DocListItemViewHolder.OnViewClickListner() {
                @Override
                public void onClick(DocumentSnapshot doc, int position) {
                    if (listner != null) {
                        listner.onItemClick(doc, position);
                    }
                }
            });
            return this.viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(documentInfo.getDocListViewDef().getList_item_shimmer_layout(), parent, false);
            this.viewHolder = new ShimmerListItemLoadingViewHolder(parent.getContext(), view);
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

    public void moveNextPage() {
        if (currentPage < maxPage && !isLoading ) {
            this.currentPage +=1;
            isLoading = true;
            dataSet.add(null);
            notifyItemInserted(dataSet.size() - 1);
            loadPage(this.currentPage);
        }
    }

    public void loadPage(int page) {

        if (runnable != null) {
            handler.removeCallbacks(runnable);
            isLoading = true;
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
                Map<String, Object> body = new HashMap<>();
                Map<String, Object> orders = getOrders();
                Map<String, Object> filter = getFilter();
                body.put("filter", filter);
                body.put("orders", orders);
                Call<PagingRecyclerViewData> call = apiInterface.listViewData("LIST_VIEW", documentName,page, 10, body);
                call.enqueue(new Callback<PagingRecyclerViewData>() {
                    @Override
                    public void onResponse(Call<PagingRecyclerViewData> call, Response<PagingRecyclerViewData> response) {
                        PagingRecyclerViewData pagingData = (PagingRecyclerViewData) response.body();
                        if (dataSet.size() > 0) {
                            dataSet.remove(dataSet.size()-1);
                            notifyItemRemoved(dataSet.size());
                        }
                        if (pagingData != null) {
                            dataSet.addAll(pagingData.getData());
                            maxPage = pagingData.getTotalPages();
                            notifyDataSetChanged();
                        }
                        isLoading = false;
                    }
                    @Override
                    public void onFailure(Call<PagingRecyclerViewData> call, Throwable t) {
                        Log.e("ERROR", t.getMessage(), t);
                        call.cancel();
                    }
                });

            }
        };

        new Handler().postDelayed(runnable, 2000);
    }

    protected Map<String, Object> getOrders() {
        return  new HashMap<>();
    }

    @Override
    protected DocListItemViewHolder createItemViewHolder(View view) {
        return null;
    }

    private class ShimmerListItemLoadingViewHolder extends DocListItemViewHolder {

        private ProgressBar progressBar;

        public ShimmerListItemLoadingViewHolder(Context context, @NonNull View itemView) {
            super(context, itemView, null, null, 0);
        }

        @Override
        protected void bindViewContent(DocumentSnapshot doc) {
        }
    }

    public interface PagingRecyclerViewAdaptaerEventListner {
        void onItemClick(DocumentSnapshot doc, int position);
    }

}
