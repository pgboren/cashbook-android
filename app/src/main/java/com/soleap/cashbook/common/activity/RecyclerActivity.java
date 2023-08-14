package com.soleap.cashbook.common.activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;
import com.soleap.cashbook.common.adapter.RecyclerViewAdapter;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.global.DocChangedEventListner;
import com.soleap.cashbook.common.global.EventHandler;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.common.global.DocCreatedEventListner;
import com.soleap.cashbook.view.DocumentInfo;

public abstract class RecyclerActivity extends BackPressActivity implements RecyclerViewAdapter.EventListner {

    public final static int ADD_NEW_ENTITY_REQUEST_CODE = 2001;
    public final static int VIEW_ENTITY_REQUEST_CODE = 2002;

    private static final String TAG = "RestApiRecyclerActivity";
    protected FloatingActionButton addFabButton;
    protected APIInterface apiInterface;
    protected RecyclerView recyclerView;
    protected PagingRecyclerViewAdapter adapter;

    protected DocumentInfo documentInfo;

    private DocChangedEventListner docChangedEventListner = new DocChangedEventListner() {
        @Override
        public void onChanged(String docId) {
            adapter.onDocChanged(docId);
        }

        @Override
        public void onError(Throwable t) {

        }
    };

    private DocCreatedEventListner docCreatedEventListner = new DocCreatedEventListner() {
        @Override
        public void onAdded(String docId) {
            adapter.onDocCreated(docId);
        }

        @Override
        public void onError(Throwable t) {
            Log.e(TAG, t.getMessage(), t);
        }
    };

    @Override
    protected void onCreatingBegin() {
        documentInfo = (DocumentInfo) getIntent().getSerializableExtra(DocumentInfo.DOCUMENT_INFO_KEY);
        EventHandler.getInstance().addDocCreatedEventListner(documentInfo.getName(), docCreatedEventListner);
        EventHandler.getInstance().addDocChangedEventListner(documentInfo.getName(), docChangedEventListner);
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    protected void initFabButtonAction() {
        addFabButton = findViewById(R.id.fab);
        if (addFabButton != null) {
            addFabButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setViewContent() {
        setContentView(documentInfo.getDocListViewDef().getView_layout());
        initActionBar();
        initFabButtonAction();
        initRecyclerViewAdapter();
        initRecyclerView();
        setTitle(ResourceUtil.getStringResourceByName(this, documentInfo.getDocListViewDef().getTitle()));
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btn_add_menu) {
            onAddNewButtonClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initRecyclerViewAdapter() {
        adapter = new PagingRecyclerViewAdapter(this,  documentInfo.getName() , documentInfo.getDocListViewDef().getList_item_layout());
        adapter.setListner(new PagingRecyclerViewAdapter.PagingRecyclerViewAdaptaerEventListner() {
            @Override
            public void onItemClick(Document doc, int position) {
                onItemClicked(doc, position);
            }
        });
        adapter.setDocumentInfo(documentInfo);
    }

    protected void onItemClicked(Document doc, int position) {
        Intent intent = new Intent(RecyclerActivity.this, documentInfo.getDocViewViewDef().getActivityClass());
        intent.putExtra(DocumentInfo.DOCUMENT_INFO_KEY, documentInfo);
        intent.putExtra(ModelViewActivity.KEY_MODEL_ID, doc.getId());
        startActivity(intent);
    }

    protected void initRecyclerView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    protected void onAddNewButtonClicked() {
        Intent intent = new Intent(RecyclerActivity.this, documentInfo.getDocAddNewDef().getActivityClass());
        intent.putExtra(DocumentInfo.DOCUMENT_INFO_KEY, documentInfo);
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
        EventHandler.getInstance().removeDocCreatedEventListner(documentInfo.getName(), docCreatedEventListner);
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
