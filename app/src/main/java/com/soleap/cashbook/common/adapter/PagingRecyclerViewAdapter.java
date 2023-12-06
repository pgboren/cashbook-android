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

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.document.PagingListingModel;
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
            this.viewHolder.setListener(new DocListItemViewHolder.DocListItemViewHolderListner() {
                @Override
                public void onClick(Document doc, int position) {
                    if (listner != null) {
                        listner.onItemClick(doc, position);
                    }
                }

                @Override
                public void onDelete(Document dcc, int position) {

                }

                @Override
                public void onEdit(Document doc, int position) {

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
        final Document doc = (Document) dataSet.get(position);
        if (doc != null) {
            DocListItemViewHolder viewHolder = (DocListItemViewHolder) holder;
            viewHolder.bind(position, doc);
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

                Call<PagingListingModel> call = apiInterface.lookupData( documentName, page, 10, body);
                call.enqueue(new Callback<PagingListingModel>() {
                    @Override
                    public void onResponse(Call<PagingListingModel> call, Response<PagingListingModel> response) {
                        PagingListingModel pagingData = (PagingListingModel) response.body();
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
                    public void onFailure(Call<PagingListingModel> call, Throwable t) {
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

    public void remove(int position) {
        this.dataSet.remove(position);
        notifyItemRemoved(position);
    }

    private class ShimmerListItemLoadingViewHolder extends DocListItemViewHolder {

        private ProgressBar progressBar;

        public ShimmerListItemLoadingViewHolder(Context context, @NonNull View itemView) {
            super(context, itemView, null, null, 0);
        }

        @Override
        protected void bindViewContent(Document doc) {
        }
    }

    public interface PagingRecyclerViewAdaptaerEventListner {
        void onItemClick(Document doc, int position);
    }

}
