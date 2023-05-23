package com.soleap.cashbook.adapter;

import android.content.Context;

import com.soleap.cashbook.Global;
import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;

public class TaskRecyclerViewAdapter extends PagingRecyclerViewAdapter {

    private String[] stages;
    private String board = Global.agile_board;

    public TaskRecyclerViewAdapter(Context context, String documentName, int viewResource) {
        super(context, documentName, viewResource);
    }
}
