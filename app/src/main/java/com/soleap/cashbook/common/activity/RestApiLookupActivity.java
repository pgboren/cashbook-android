package com.soleap.cashbook.common.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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

public abstract class RestApiLookupActivity<T extends Document> extends BackPressActivity implements RestApiRecyclerViewAdapter.OnItemListener {

    public final static String LOOK_UP_ACTIVITY = "RestApiLookupActivity";
    public final static String KEY_RETURN_BACK = "KEY_RETURN_BACK";
    public final static String KEY_TARGET_ENTITY = "KEY_TARGET_ENTITY";

    public final static int ON_ADD_NEW_ENTITY_ACTIVITY_REQUEST_CODE = 2001;
    public final static int ON_ENTITY_SELECTED_REQUEST_CODE = 20002;

    private static final String TAG = "RestApiRecyclerActivity";
    private SearchView searchView;

    private boolean returnBack = true;

    protected APIInterface apiInterface;

    protected RecyclerView recyclerView;

    protected RestApiRecyclerViewAdapter adapter;

    private Class targetEntityClass;

    abstract Class getAddNewActivityClass();

    protected void doApiCall() {
        Call<List<T>> call = getAPICall();
        call.enqueue(new Callback<List<T>>() {
            @Override
            public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                List<T> models = response.body();
                adapter.setDataSet(models);
            }
            @Override
            public void onFailure(Call<List<T>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    abstract RestApiRecyclerViewAdapter getAdapter();

    abstract Call<List<T>> getAPICall();

    @Override
    protected void configureActionBar() {
        super.configureActionBar();
    }

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
        adapter.setListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            returnBack = extras.getBoolean(KEY_RETURN_BACK);
            if (!returnBack) {
                targetEntityClass = (Class) extras.get(KEY_TARGET_ENTITY);
            }
        }

        doApiCall();
    }

    protected void onAddNewButtonClicked() {
        Intent intent = new Intent(this, getAddNewActivityClass());
        intent.putExtra(RestApiModelFormActivity.ALLOW_SAVE_AND_ADD_NEW_EXTRAS, false);
        startActivityForResult(intent, ON_ADD_NEW_ENTITY_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                doApiCall();
            }
        }
        catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    @Override
    public void onItemSelected(int position, Document selModel) {
        if (!returnBack) {
            Intent intent = new Intent(this, targetEntityClass);
            startActivity(intent);
        }
        else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(LOOK_UP_ACTIVITY, selModel.get_id());
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                filter(query);
                return false;
            }
        });

        return true;
    }

    private void filter(String queryString) {
//        query = db.collection(getCollectionName()).orderBy(getListOrder()).startAt(queryString).endAt(queryString+'\uf8ff');
//        adapter.setQuery(query);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

}
