package com.soleap.cashbook.common.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;
import com.soleap.cashbook.common.adapter.RecyclerViewAdapter;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.view.Views;

public abstract class RecyclerActivity extends BackPressActivity implements RecyclerViewAdapter.EventListner {

    public final static int ADD_NEW_ENTITY_REQUEST_CODE = 2001;
    public final static int VIEW_ENTITY_REQUEST_CODE = 2002;


    private static final String TAG = "RestApiRecyclerActivity";

    protected FloatingActionButton addFabButton;
    protected String documentName;
    protected APIInterface apiInterface;
    protected RecyclerView recyclerView;
    protected PagingRecyclerViewAdapter adapter;

    @Override
    protected void onCreatingBegin() {
        this.documentName = getIntent().getExtras().getString(DocumentName.DOCUMENT_NAME);
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    protected void initFabButtonAction() {
        addFabButton = findViewById(R.id.fab);
        addFabButton.setVisibility(View.GONE);
        addFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewButtonClicked();
            }
        });
    }

    @Override
    protected void setViewContent() {
        setContentView(DocumentName.getInstance(this).getListActivityLayout(documentName));
        initActionBar();
        initFabButtonAction();
        initRecyclerViewAdapter();
        initRecyclerView();
        setTitle(DocumentName.getInstance(this).getListTitle(documentName));
        startDataListening();
    }

    protected void initActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_item_action_toolbar_menu, menu);
        return true;
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
        adapter = new PagingRecyclerViewAdapter(this, documentName, DocumentName.getInstance(this).getListItemLayoutView(documentName));
        adapter.setItemView(Views.CONTACT_LIST_ITEM_VIEW);
        adapter.setListner(this);
        if (DocumentName.getInstance(this).getAddNewActivityClass(documentName) != null) {
            adapter.setAddNewActivityClass(DocumentName.getInstance(this).getAddNewActivityClass(documentName));
        }

        if (DocumentName.getInstance(this).getViewActivityClass(documentName) != null) {
            adapter.setViewActivityClass(DocumentName.getInstance(this).getViewActivityClass(documentName));
        }
        adapter.setListner(this);
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
        intent.putExtra(DocumentName.DOCUMENT_NAME, documentName);
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

    @Override
    public void onItemSelected(DocumentSnapshot documentSnapshot) {
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
