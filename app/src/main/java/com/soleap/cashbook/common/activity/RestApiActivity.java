package com.soleap.cashbook.common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.soleap.cashbook.R;
import com.soleap.cashbook.widget.MyDividerItemDecoration;

public abstract class RestApiActivity<T> extends BackPressActivity {

    public static final String KEY_MODEL_ID = "key_model_id";
    protected RecyclerView recyclerView;
    protected FirebaseFirestore db = FirebaseFirestore.getInstance();
    protected CollectionReference collectionRef;
    protected Query query;

    abstract String getCollectionName();

    abstract String getListOrder();

    abstract Class getCollectionClass();

    abstract Class getAddNewActivityClass();

    abstract Class getEditActivityClass();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    protected void onAddNewButtonClicked() {
        Intent intent = new Intent(this, getAddNewActivityClass());
        startActivity(intent);
    }



}
