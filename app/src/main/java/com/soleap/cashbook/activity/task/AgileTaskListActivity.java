package com.soleap.cashbook.activity.task;

import android.os.Handler;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.chip.ChipGroup;
import com.soleap.cashbook.R;
import com.soleap.cashbook.adapter.TaskRecyclerViewAdapter;
import com.soleap.cashbook.common.activity.RecyclerActivity;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.widget.bottomsheetmenu.MenuItem;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.viewholder.BsDocViewHolder;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;
import com.soleap.cashbook.widget.agile.StageChipGroup;

public class AgileTaskListActivity extends RecyclerActivity implements BottomSheetMenu.BottomSheetMenuItemClickListener {

    private static final String TAG = "AgileTaskListActivity";
    private StageChipGroup chipStage;
    private String newStage = "63e9dcec0759ba3dc06bfff4";

    @Override
    protected void bindListItemViewHolder(View itemView, int position, DocumentSnapshot doc) {
        BsDocViewHolder viewHolder = new BsDocViewHolder(this, documentName);
        viewHolder.bind(itemView, position, doc);
    }

    @Override
    protected void setViewContent() {
        super.setViewContent();
    }

    @Override
    protected void initRecyclerView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        chipStage = findViewById(R.id.stageChip);
        chipStage.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                loadTasks(chipStage.getSelectedStage());
            }
        });
    }

    @Override
    protected void startDataListening() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadTasks(chipStage.getSelectedStage());
            }
        }, 500);
    }

    private void loadTasks(String stageId) {
        if (stageId.equals(newStage)) {
            addFabButton.setVisibility(View.VISIBLE);
        }
        else {
            addFabButton.setVisibility(View.GONE);
        }
        TaskRecyclerViewAdapter taskRecyclerViewAdapter = (TaskRecyclerViewAdapter)adapter;
        String[] stages = new String[1];
        stages[0] = stageId;
        taskRecyclerViewAdapter.startListening(stages);
    }

    @Override
    public void onItemClick(MenuItem item) {

    }

    @Override
    protected void initRecyclerViewAdapter() {
        final ShimmerFrameLayout container = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout);
        adapter = new TaskRecyclerViewAdapter(this, documentName, DocumentInfo.getInstance(this).getListItemLayout(documentName));
        if (DocumentInfo.getInstance(this).getAddNewActivityClass(documentName) != null) {
            adapter.setAddNewActivityClass(DocumentInfo.getInstance(this).getAddNewActivityClass(documentName));
        }

        if (DocumentInfo.getInstance(this).getViewActivityClass(documentName) != null) {
            adapter.setViewActivityClass(DocumentInfo.getInstance(this).getViewActivityClass(documentName));
        }
    }
}
