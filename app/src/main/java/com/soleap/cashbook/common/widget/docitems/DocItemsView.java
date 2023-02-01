package com.soleap.cashbook.common.widget.docitems;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.activity.SaleOrderitemViewActivity;
import com.soleap.cashbook.common.activity.BsDocLookUpActivity;
import com.soleap.cashbook.common.util.MedialUtils;
import com.soleap.cashbook.document.DocumentInfo;

public abstract class DocItemsView extends LinearLayout {

    private RecyclerView recyclerView;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    public DocItemsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DocItemsView, 0, 0);
        String title = a.getString(R.styleable.DocItemsView_div_title);

        a.recycle();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_input_doc_items, this, true);
        TextView txtLabel = findViewById(R.id.label);
        txtLabel.setText(title);
        recyclerView = findViewById(R.id.recyclerView_doc_items);
        DocItemsViewAdapter adapter = createAdapter(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Button btnAddItem = findViewById(R.id.btn_add_item);
        btnAddItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SaleOrderitemViewActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        AppCompatActivity activity = (AppCompatActivity) context;
        activityResultLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                        }


                    }
                }
        );
    }

    protected  abstract DocItemsViewAdapter createAdapter(Context context);

}
