package com.soleap.cashbook.activity.task;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.soleap.cashbook.Global;
import com.soleap.cashbook.R;
import com.soleap.cashbook.activity.ActivityProviderFactory;
import com.soleap.cashbook.adapter.TaskRecyclerViewAdapter;
import com.soleap.cashbook.common.activity.ModelViewActivity;
import com.soleap.cashbook.common.activity.RecyclerActivity;
import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.widget.bottomsheetmenu.MenuItem;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;
import com.soleap.cashbook.common.widget.bottomsheetmenu.BottomSheetMenu;
import com.soleap.cashbook.widget.agile.StageChipGroup;

public class AgileTaskListActivity extends RecyclerActivity implements BottomSheetMenu.BottomSheetMenuItemClickListener {

    private static final String TAG = "AgileTaskListActivity";
    private StageChipGroup chipStage;
    private String newStage = Global.agile_stage;
    private String board = Global.agile_board;
    private String seletingStage = null;

    @Override
    protected void setViewContent() {
        super.setViewContent();
        this.seletingStage = getIntent().getStringExtra(DocumentName.DOCUMENT_ID);
        Log.d(TAG, this.seletingStage);
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
        chipStage.setStageValueChangedListner(new StageChipGroup.OnStageValueChangedListner() {
            @Override
            public void OnChange(String value) {
                loadTasks(value);
            }
        });
        initScrollListener();
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
                        ((PagingRecyclerViewAdapter)adapter).moveNextPage();
                    }
                }
        });
    }

    @Override
    protected void startDataListening() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                loadTasks(chipStage.getSelectedStage());
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
        taskRecyclerViewAdapter.loadPage(1);

    }

    @Override
    public void onItemClick(MenuItem item) {
    }

    @Override
    protected void initRecyclerViewAdapter() {
        final ShimmerFrameLayout container = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout);
        adapter = new TaskRecyclerViewAdapter(this, documentName, DocumentName.getInstance(this).getListItemLayoutView(documentName));
        adapter.setListner(this);
        if (DocumentName.getInstance(this).getAddNewActivityClass(documentName) != null) {
            adapter.setAddNewActivityClass(DocumentName.getInstance(this).getAddNewActivityClass(documentName));
        }

        if (DocumentName.getInstance(this).getViewActivityClass(documentName) != null) {
            adapter.setViewActivityClass(DocumentName.getInstance(this).getViewActivityClass(documentName));
        }
        adapter.setListner(this);
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        this.chipStage.setChipEnable(false);
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
        this.chipStage.setChipEnable(true);
    }

    @Override
    public void onItemSelected(DocumentSnapshot doc) {
        Intent intent = new Intent(this, ActivityProviderFactory.getViewActivity(documentName));
        intent.putExtra(DocumentName.DOCUMENT_NAME, documentName);
        intent.putExtra(ModelViewActivity.KEY_DOC, doc);
        intent.putExtra(ModelViewActivity.KEY_MODEL_ID, doc.getId());
        startActivity(intent);
    }
}
