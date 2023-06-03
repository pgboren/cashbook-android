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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.DocItemsViewActivity;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.util.DialogUtils;
import com.soleap.cashbook.common.widget.OnValueChanged;
import com.soleap.cashbook.common.widget.recyclerview.SwipeToDeleteCallback;
import com.soleap.cashbook.common.widget.recyclerview.SwipeToDeleteRecyclerViewAdapter;

import java.util.List;

public abstract class DocItemsView extends LinearLayout {

    private RecyclerView recyclerView;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    protected List<Document> dataSet = null;
    private Boolean allowManyItems = true;
    private SwipeToDeleteRecyclerViewAdapter adapter;
    private Button btnAddItem;
    private CoordinatorLayout coordinatorLayout;
    private OnValueChanged onValueChangedListner = null;

    public void setOnValueChangedListner(OnValueChanged onValueChangedListner) {
        this.onValueChangedListner = onValueChangedListner;
    }

    public DocItemsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DocItemsView, 0, 0);
        String title = a.getString(R.styleable.DocItemsView_div_title);
        allowManyItems = a.getBoolean(R.styleable.DocItemsView_div_allow_many_items, true);
        a.recycle();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_input_doc_items, this, true);
        TextView txtLabel = findViewById(R.id.label);
        txtLabel.setText(title);
        recyclerView = findViewById(R.id.recyclerView_doc_items);
        adapter = createAdapter(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        btnAddItem = findViewById(R.id.btn_add_item);
        btnAddItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(context, SaleOrderitemInputActivity.class);
                //activityResultLauncher.launch(intent);
            }
        });

        enableSwipeToDeleteAndUndo();

        AppCompatActivity activity = (AppCompatActivity) context;
        activityResultLauncher = activity.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Document document = (Document) data.getSerializableExtra(DocItemsViewActivity.ITEM_DOC);
                            dataSet.add(document);
                            if (dataSet.size() >= 1 && allowManyItems == false) {
                                btnAddItem.setVisibility(GONE);
                            }
                            adapter.notifyDataSetChanged();
                            if (onValueChangedListner != null) {
                                onValueChangedListner.onChanged(dataSet);
                            }
                        }
                    }
                }
        );
    }

    protected  abstract SwipeToDeleteRecyclerViewAdapter createAdapter(Context context);

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                Document item = (Document) adapter.getData().get(position);
                adapter.removeItem(position);
                btnAddItem.setVisibility(VISIBLE);

                String msg_message = getContext().getString(R.string.delete_alert_dialog_message);
                String doc_name = getContext().getString(R.string.item);
                String msg_yes = getContext().getString(R.string.yes);
                String msg_no = getContext().getString(R.string.no);

                DialogUtils.askForDeleteDialog(getContext(), String.format(msg_message, doc_name), msg_yes, msg_no, null, new DialogUtils.OnDialogButtonClick() {
                    @Override
                    public void onClick() {
                        adapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                        btnAddItem.setVisibility(GONE);
                    }
                });
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    public List<Document> getValue() {
        return dataSet;
    }

    public void setValue(List<Document> values) {
        this.dataSet = values;
        adapter.notifyDataSetChanged();
    }
}
