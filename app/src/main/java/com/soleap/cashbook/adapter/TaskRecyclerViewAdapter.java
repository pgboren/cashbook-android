package com.soleap.cashbook.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;
import com.soleap.cashbook.common.adapter.RecyclerViewAdapter;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.repository.AgileTaskRepository;
import com.soleap.cashbook.viewholder.BsDocViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TaskRecyclerViewAdapter extends PagingRecyclerViewAdapter {

    public TaskRecyclerViewAdapter(Context context, String documentName, int viewResource) {
        super(context, documentName, viewResource);
    }
}
