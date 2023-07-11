package com.soleap.cashbook.common.widget.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.util.DimensionUtils;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.widget.dialog.AddNewDocFragmentSuportDialog;
import com.soleap.cashbook.common.widget.dialog.OneToManyDocFragmentSuportDialog;
import com.soleap.cashbook.common.widget.recyclerview.DragDropItemTouchRecyclerViewAdapter;
import com.soleap.cashbook.common.widget.recyclerview.StartDragListener;
import com.soleap.cashbook.document.Color;
import com.soleap.cashbook.document.ItemSpecification;
import com.soleap.cashbook.view.DocumentInfo;

import java.util.List;

public class OneToManyDocRecyclerInputView<T> extends LinearLayout {

    private String label;

    private TextView tvLabel;

    private RecyclerView recyclerView;

    private DocumentInfo documentInfo;

    private List<Document> value;

    protected OneToManyDocRecyclerViewAdapter adapter;

    private OneToManyDocFragmentSuportDialog inputDialog;

    public List<Document> getValue() {
        return value;
    }

    public void addValue(Document doc) {
        value.add(doc);
    }

    public OneToManyDocRecyclerInputView(Context context, @Nullable AttributeSet attrs, DocumentInfo documentInfo) {
        super(context);
        setOrientation(VERTICAL);
        this.documentInfo = documentInfo;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OneToManyDocRecyclerInputView, 0, 0);

        inputDialog = new OneToManyDocFragmentSuportDialog(context);

        LinearLayout labelContainer = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        labelContainer.setLayoutParams(params);
        addView(labelContainer);

        tvLabel = new TextView(context);
        params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        tvLabel.setLayoutParams(params);
        tvLabel.setTextSize(16);
        tvLabel.setTextColor(context.getColor(R.color.blue_light));
        labelContainer.addView(tvLabel);

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.ic_add_circle);

        imageView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.1f ));
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDialog.setTitle("NEW_ITEM_SPECIFICATION");
                AppCompatActivity activity = (AppCompatActivity) context;
                inputDialog.setListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        addNewItem();
                    }
                });
                inputDialog.show(activity.getSupportFragmentManager(), "Expanded");
            }
        });
        labelContainer.addView(imageView);

        TextView tvPrompt = new TextView(context);
        params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,DimensionUtils.dpToPx(context, 10));
        tvPrompt.setLayoutParams(params);
        tvPrompt.setTextSize(12);
        tvPrompt.setText(context.getString(R.string.item_spec_promt));
        tvPrompt.setTextColor(context.getColor(R.color.gray_light));
        addView(tvPrompt);

        recyclerView = new RecyclerView(context);
        params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        recyclerView.setLayoutParams(params);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        initRecyclerViewAdapter();
        addView(recyclerView);
        processAttributeSet(context, attrs, a);
        a.recycle();
    }

    private void addNewItem() {
        adapter.addItem(inputDialog.getDoc());
    }

    protected void processAttributeSet(Context context, @Nullable AttributeSet attrs, TypedArray a) {
        String label = a.getString(R.styleable.OneToManyDocRecyclerInputView_otmview_label);
        tvLabel.setText(label);
    }
    protected void initRecyclerViewAdapter() {
        adapter = new OneToManyDocRecyclerViewAdapter(getContext(), documentInfo.getDocListViewDef().getList_item_layout(), new StartDragListener() {
            @Override
            public void requestDrag(RecyclerView.ViewHolder viewHolder) {

            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setRecyclerView(recyclerView);
    }
}
