package com.soleap.cashbook.common.widget.lookup;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
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
import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.global.EventHandler;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.view.DocumentInfo;

public class DocumentListBottomSheetFragment extends BottomSheetDialogFragment {
    private final String message;
    private final boolean isExpanded;

    private DocumentInfo documentInfo;

    protected APIInterface apiInterface;

    private TextView tvTitle;

    private DocumentListBottomSheetFragmentEventListner eventListner;

    private View contentView;
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDocumentInfo(DocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
    }

    public void setEventListner(DocumentListBottomSheetFragmentEventListner eventListner) {
        this.eventListner = eventListner;
    }

    public DocumentListBottomSheetFragment(boolean isExpanded, String message) {
        super();
        this.message = message;
        this.isExpanded = isExpanded;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View lookupView = inflater.inflate(R.layout.bottom_sheet_lookup_view, container, false);
        return lookupView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (isExpanded) {
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
        return super.onCreateDialog(savedInstanceState);
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
        initRecyclerViewAdapter();
        initRecyclerView(view);
    }

    private RecyclerView recyclerView;
    protected PagingRecyclerViewAdapter adapter;

    protected void initRecyclerView(View view) {
        tvTitle = view.findViewById(R.id.tv_tititle);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        initScrollListener();
        tvTitle.setText(title);
        startDataListening();
    }

    protected void initRecyclerViewAdapter() {
        adapter = new PagingRecyclerViewAdapter(getContext(),  documentInfo.getName() , documentInfo.getDocListViewDef().getList_item_layout());
        adapter.setListner(new PagingRecyclerViewAdapter.PagingRecyclerViewAdaptaerEventListner() {
            @Override
            public void onItemClick(Document doc, int position) {
                eventListner.onItemSelected(doc);
                dismiss();
            }
        });
        adapter.setDocumentInfo(documentInfo);
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
                    ((PagingRecyclerViewAdapter)adapter).moveNextPage();
                }
            }
        });
    }

    protected void startDataListening() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PagingRecyclerViewAdapter pagingRecyclerViewAdapter = (PagingRecyclerViewAdapter)adapter;
                pagingRecyclerViewAdapter.loadPage(1);
            }
        }, 500);
    }

}