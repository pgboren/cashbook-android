package com.soleap.cashbook.common.widget.agile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.activity.AgileTaskListActivity;
import com.soleap.cashbook.common.adapter.RecyclerViewAdapter;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.viewholder.BsDocViewHolder;

public class AgileStageSummaryView extends LinearLayout implements RecyclerViewAdapter.EventListner {

    String stageId = null;

    public AgileStageSummaryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AgileStageSummaryView, 0, 0);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_dashboard_task_summary, this, true);
        RecyclerView rvStageView = findViewById(R.id.rv_stage_summary);
        rvStageView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), DocumentInfo.AGILE_STAGE, R.layout.agile_stage_summary_view_item) {
            @Override
            protected ViewHolder createItemViewHolder(View itemView) {
                return new ViewHolder(itemView) {
                    @Override
                    protected void bind( int position, DocumentSnapshot data) {
                        bindListItemViewHolder(this.itemView, position, data);
                    }
                };
            }
        };
        adapter.setListner(this);
        rvStageView.setHasFixedSize(true);
        rvStageView.setAdapter(adapter);
        adapter.startListening();
    }

    protected void bindListItemViewHolder(View itemView, int position, DocumentSnapshot doc) {
        BsDocViewHolder viewHolder = new AgileStageSummaryItemViewHolder((Activity) getContext(), DocumentInfo.AGILE_STAGE);
        viewHolder.bind(itemView, position, doc);
    }

    @Override
    public void onItemSelected(DocumentSnapshot documentSnapshot) {
        Intent intent = new Intent(getContext(), AgileTaskListActivity.class);
        intent.putExtra(DocumentInfo.DOCUMENT_NAME, DocumentInfo.AGILE_TASK);
        intent.putExtra(DocumentInfo.DOCUMENT_ID, documentSnapshot.getId());
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
