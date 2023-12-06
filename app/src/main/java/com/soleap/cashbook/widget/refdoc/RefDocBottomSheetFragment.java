package com.soleap.cashbook.widget.refdoc;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.RefDocument;
import com.soleap.cashbook.view.DocumentInfo;

public class RefDocBottomSheetFragment extends BottomSheetDialogFragment {

    private TextView tvTitle;

    private EventListner eventListner;

    private View contentView;
    private String title;

    private DocumentInfo documentInfo;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEventListner(EventListner eventListner) {
        this.eventListner = eventListner;
    }

    public RefDocBottomSheetFragment(DocumentInfo documentInfo, EventListner listner) {
        super();
        this.documentInfo = documentInfo;
        this.eventListner = listner;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View lookupView = inflater.inflate(R.layout.bottom_sheet_lookup_view, container, false);
        return lookupView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setOnShowListener(dialog -> {
            FrameLayout bottomSheet = ((BottomSheetDialog) dialog).findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

        });
        return bottomSheetDialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView = view;
        ImageButton btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        initRecyclerView(view);
    }

    private RecyclerView recyclerView;

    protected RefDocAdapter adapter;

    protected void initRecyclerView(View view) {
        tvTitle = view.findViewById(R.id.tv_title);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        dividerItemDecoration.setDrawable(getContext().getDrawable(R.drawable.line_divider));
        recyclerView.setHasFixedSize(true);
        adapter = new RefDocAdapter(getContext(), documentInfo.getName(), new RefDocAdapter.EventListner() {
            @Override
            public void onItemClick(RefDocument refDoc, int position) {
                dismiss();
                eventListner.onItemClick(refDoc, position);
            }
        });

        recyclerView.setAdapter(adapter);
        tvTitle.setText("Category");
    }

    public interface EventListner {
        void onItemClick(RefDocument refDoc, int position);
    }

}