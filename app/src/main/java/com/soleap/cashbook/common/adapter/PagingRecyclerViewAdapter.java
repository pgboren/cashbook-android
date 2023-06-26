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
import com.soleap.cashbook.common.repository.DocumentRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;
import com.soleap.cashbook.viewholder.ListItemViewHolderFactory;

import retrofit2.Response;

public class PagingRecyclerViewAdapter extends RecyclerViewAdapter {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private int currentPage = 1;
    private int maxPage = -1;

    private boolean isFirstBind = true;

    private PagingRecyclerViewAdaptaerEventListner listner;

    public void setListner(PagingRecyclerViewAdaptaerEventListner listner) {
        this.listner = listner;
    }

    public PagingRecyclerViewAdapter(Context context, String documentName, int viewResource) {
        super(context, documentName, viewResource);
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

    @Override
    protected void initRepository() {
        this.repository = RepositoryFactory.create().get(documentName);
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

        if (page == 1) {
            dataSet.clear();
            notifyDataSetChanged();
            dataSet.add(null);
            notifyItemInserted(dataSet.size() - 1);
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                repository.list(page, new DocumentRepository.DocumentEventListner() {
                            @Override
                            public void onError(Throwable t) {
                                Log.e("ERROR", t.getMessage(), t);
                            }

                            @Override
                            public void onResponse(Response response) {
                                PagingRecyclerViewData pagingData = (PagingRecyclerViewData) response.body();
                                if (dataSet.size() > 0) {
                                    dataSet.remove(dataSet.size()-1);
                                    notifyItemRemoved(dataSet.size());
                                }
                                dataSet.addAll(pagingData.getData());
                                maxPage = pagingData.getTotalPages();
                                notifyDataSetChanged();
                                isLoading = false;
                            }
                        });
            }
        };

        new Handler().postDelayed(runnable, 2000);
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
