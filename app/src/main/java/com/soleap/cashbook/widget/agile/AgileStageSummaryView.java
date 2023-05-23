package com.soleap.cashbook.widget.agile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.activity.task.AgileTaskListActivity;
import com.soleap.cashbook.common.adapter.RecyclerViewAdapter;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;

public class AgileStageSummaryView extends LinearLayout implements RecyclerViewAdapter.EventListner {

    String stageId = null;

    public AgileStageSummaryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AgileStageSummaryView, 0, 0);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_dashboard_task_summary, this, true);
        RecyclerView rvStageView = findViewById(R.id.rv_stage_summary);
        rvStageView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), DocumentName.AGILE_STAGE, R.layout.agile_stage_summary_view_item) {
//            @Override
//            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//            }
//
//            @Override
//            protected DocListItemViewHolder createItemViewHolder(View view) {
//
//            }
//
//        };
//        adapter.setListner(this);
        rvStageView.setHasFixedSize(true);
//        rvStageView.setAdapter(adapter);
//        adapter.startListening();
    }

    protected void bindListItemViewHolder(View itemView, int position, DocumentSnapshot doc) {
//        DocListItemViewHolder viewHolder = new AgileStageSummaryItemViewHolder((Activity) getContext(), DocumentName.AGILE_STAGE);
//        viewHolder.bind(itemView, position, doc);
    }

    @Override
    public void onItemSelected(DocumentSnapshot documentSnapshot) {
        Intent intent = new Intent(getContext(), AgileTaskListActivity.class);
        intent.putExtra(DocumentName.DOCUMENT_NAME, DocumentName.AGILE_TASK);
        intent.putExtra(DocumentName.DOCUMENT_ID, documentSnapshot.getId());
        getContext().startActivity(intent);
    }

    @Override
    public void onStartListening() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onStopListening() {

    }
}
