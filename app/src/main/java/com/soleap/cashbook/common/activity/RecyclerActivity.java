package com.soleap.cashbook.common.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.soleap.cashbook.R;
import com.soleap.cashbook.activity.ActivityProviderFactory;
import com.soleap.cashbook.common.adapter.RecyclerViewAdapter;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.document.DocumentInfo;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

public abstract class RecyclerActivity extends BackPressActivity {

    public final static int ADD_NEW_ENTITY_REQUEST_CODE = 2001;
    public final static int VIEW_ENTITY_REQUEST_CODE = 2002;


    private static final String TAG = "RestApiRecyclerActivity";

    protected FloatingActionButton addFabButton;
    protected String documentName;
    protected APIInterface apiInterface;
    protected RecyclerView recyclerView;
    protected RecyclerViewAdapter adapter;

    protected abstract void bindListItemViewHolder(View itemView, int position, DocumentSnapshot doc);

    @Override
    protected void onCreatingBegin() {
        this.documentName = getIntent().getExtras().getString(DocumentInfo.DOCUMENT_NAME);
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    protected void initFabButtonAction() {
        addFabButton = findViewById(R.id.fab);
        addFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewButtonClicked();
            }
        });
    }

    @Override
    protected void setViewContent() {
        setContentView(DocumentInfo.getInstance(this).getListActivityLayout(documentName));
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            setSupportActionBar(toolbar);
        }

        initFabButtonAction();
        initRecyclerViewAdapter();
        initRecyclerView();
        setTitle(DocumentInfo.getInstance(this).getListTitle(documentName));
        startDataListening();
    }

    protected void startDataListening() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.startListening();
            }
        }, 500);
    }

    protected void initRecyclerViewAdapter() {
        final ShimmerFrameLayout container = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout);
        adapter = new RecyclerViewAdapter(this, documentName, DocumentInfo.getInstance(this).getListItemLayout(documentName)) {
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

        if (DocumentInfo.getInstance(this).getAddNewActivityClass(documentName) != null) {
            adapter.setAddNewActivityClass(DocumentInfo.getInstance(this).getAddNewActivityClass(documentName));
        }


        if (DocumentInfo.getInstance(this).getViewActivityClass(documentName) != null) {
            adapter.setViewActivityClass(DocumentInfo.getInstance(this).getViewActivityClass(documentName));
        }
    }

    protected void initRecyclerView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    protected void onAddNewButtonClicked() {
        adapter.addNew();
    }

    protected void onDocItemSelected(DocumentSnapshot doc) {
        Intent intent = new Intent(RecyclerActivity.this, ActivityProviderFactory.getViewActivity(documentName));
        intent.putExtra(DocumentInfo.DOCUMENT_NAME, documentName);
        intent.putExtra(ModelViewActivity.KEY_DOC, doc);
        intent.putExtra(ModelViewActivity.KEY_MODEL_ID, doc.getId());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void finish() {
        this.adapter.notifyActivityFinish();
        super.finish();
    }
}
