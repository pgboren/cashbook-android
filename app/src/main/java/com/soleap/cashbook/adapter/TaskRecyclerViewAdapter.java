package com.soleap.cashbook.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.soleap.cashbook.common.adapter.RecyclerViewAdapter;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.repository.AgileTaskRepository;
import com.soleap.cashbook.viewholder.BsDocViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerViewAdapter {

    public TaskRecyclerViewAdapter(Context context, String documentName, int viewResource) {
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
    protected void initRepository() {
        this.repository = RepositoryFactory.create().get(documentName);
        this.repository.setListDocumentListner(this);
        this.repository.addOnCreatedDocumentListner(documentName, this);
        this.repository.addOnChangedDocumentListner(documentName, this);
        this.repository.addOnCreatedDocumentListner(documentName, this);
        this.repository.addOnRemovedDocumentListner(documentName, this);
    }

    public void startListening(String[] stages) {
        AgileTaskRepository taskRepository = (AgileTaskRepository)repository;
        taskRepository.getTasks(stages);
        if (listner != null) {
            this.listner.onStartListening();
        }
    }

    @Override
    public void startListening() {
    }
}
