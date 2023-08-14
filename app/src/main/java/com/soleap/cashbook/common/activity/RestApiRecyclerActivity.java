package com.soleap.cashbook.common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.adapter.RestApiRecyclerViewAdapter;
import com.soleap.cashbook.viewholder.widget.MyDividerItemDecoration;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RestApiRecyclerActivity<T extends Document> extends BackPressActivity implements RestApiRecyclerViewAdapter.OnItemListener {

    public final static int ADD_NEW_ENTITY_REQUEST_CODE = 2001;
    public final static int VIEW_ENTITY_REQUEST_CODE = 2002;

    private static final String TAG = "RestApiRecyclerActivity";

    protected APIInterface apiInterface;

    protected RecyclerView recyclerView;

    protected RecyclerView.Adapter adapter;

    abstract Class getAddNewActivityClass();

    abstract Class getEditActivityClass();

    abstract Class getViewActivityClass();

    abstract Call<List<T>> getAPICall();

    abstract RecyclerView.Adapter getAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewButtonClicked();
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 0));
        adapter = getAdapter();
        if (adapter instanceof RestApiRecyclerViewAdapter) {
            RestApiRecyclerViewAdapter restAdapter = (RestApiRecyclerViewAdapter)adapter;
            restAdapter.setListener(this);
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        doApiCall();
    }

    protected void onAddNewButtonClicked() {
        Intent intent = new Intent(this, getAddNewActivityClass());
        startActivityForResult(intent, ADD_NEW_ENTITY_REQUEST_CODE);
    }

    protected void onNewEntityAdded(T[] entities) {
        for (T model: entities) {
//            adapter.add(model);
        }
        adapter.notifyDataSetChanged();
    }

    protected void onActivityResultOK(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == ADD_NEW_ENTITY_REQUEST_CODE) {
            T[] models = (T[])data.getExtras().getSerializable("entities");
            onNewEntityAdded(models);
        }

        if (requestCode == VIEW_ENTITY_REQUEST_CODE) {
            boolean isModified = data.getExtras().getBoolean(ModelViewActivity.KEY_MODIFIED_FLAG);
            int position = data.getExtras().getInt(ModelViewActivity.KEY_SELECTED_POSITION);
            if (isModified) {
                T model = (T)data.getExtras().getSerializable(ModelViewActivity.KEY_MODEL);
//                adapter.set(position, model);
            }
            boolean isDeleted = data.getExtras().getBoolean(ModelViewActivity.KEY_DELETED_FLAG);
            if (isDeleted) {
//                adapter.remove(position);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                onActivityResultOK(requestCode, resultCode, data);
            }
        }
        catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    @Override
    public void onItemSelected(int position, Document selModel) {
        Intent intent = new Intent(this, getViewActivityClass());
        intent.putExtra(ModelViewActivity.KEY_MODEL, selModel);
        intent.putExtra(ModelViewActivity.KEY_MODEL_ID, selModel.getId());
        intent.putExtra(ModelViewActivity.KEY_SELECTED_POSITION, position);
        startActivityForResult(intent, VIEW_ENTITY_REQUEST_CODE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected void doApiCall() {
        Call<List<T>> call = getAPICall();
        call.enqueue(new Callback<List<T>>() {
            @Override
            public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                List<T> models = response.body();
//                adapter.setDataSet(models);
            }

            @Override
            public void onFailure(Call<List<T>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

}
