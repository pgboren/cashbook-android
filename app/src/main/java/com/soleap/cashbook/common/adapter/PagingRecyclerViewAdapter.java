package com.soleap.cashbook.common.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.common.activity.RecyclerActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.PagingRecyclerViewData;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.repository.AgileTaskRepository;
import com.soleap.cashbook.viewholder.BsDocViewHolder;

import java.util.List;

public class PagingRecyclerViewAdapter extends RecyclerViewAdapter implements DocumentSnapshotRepository.OnGetPagingDocsRequestListner {

    private int currentPage = 1;
    private int maxPage = -1;
    private String[] stages;

    public PagingRecyclerViewAdapter(Context context, String documentName, int viewResource) {
        super(context, documentName, viewResource);
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

    public void moveNextPage() {
        this.currentPage +=1;
        loadMore(this.stages, this.currentPage);
    }

    public void loadMore(String[] stages, int page) {
        this.stages = stages;
        AgileTaskRepository taskRepository = (AgileTaskRepository)repository;
        taskRepository.getTasks(stages, currentPage);
        if (listner != null) {
            this.listner.onStartListening();
        }
    }

    @Override
    public void startListening() {
    }

    @Override
    public void onGet(PagingRecyclerViewData pagingData) {
        this.dataSet.addAll(pagingData.getData());
        maxPage = pagingData.getTotalPages();
        this.notifyDataSetChanged();
    }
}
