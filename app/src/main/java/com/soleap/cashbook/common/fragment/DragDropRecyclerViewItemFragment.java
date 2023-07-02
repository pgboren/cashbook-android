package com.soleap.cashbook.common.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.adapter.PagingRecyclerViewAdapter;
import com.soleap.cashbook.common.widget.lookup.DocumentListBottomSheetFragmentEventListner;
import com.soleap.cashbook.common.widget.recyclerview.DragDropItemTouchRecyclerViewAdapter;
import com.soleap.cashbook.common.widget.recyclerview.ItemMoveCallback;
import com.soleap.cashbook.common.widget.recyclerview.StartDragListener;
import com.soleap.cashbook.restapi.APIInterface;
import com.soleap.cashbook.view.DocumentInfo;

import java.util.Map;

public class DragDropRecyclerViewItemFragment extends Fragment {
    private final String message;
    private final boolean isExpanded;

    private boolean isAllowAdd = false;

    public void setAllowAdd(boolean allowAdd) {
        isAllowAdd = allowAdd;
    }

    private DocumentInfo documentInfo;

    protected APIInterface apiInterface;

    private TextView tvTitle;

    private DocumentListBottomSheetFragmentEventListner eventListner;
    private String title;

    private ItemTouchHelper touchHelper;
    private RecyclerView recyclerView;

    protected DragDropItemTouchRecyclerViewAdapter adapter;

    private Map<String, Object> filter;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDocumentInfo(DocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
    }

    public void setEventListner(DocumentListBottomSheetFragmentEventListner eventListner) {
        this.eventListner = eventListner;
    }

    public DragDropRecyclerViewItemFragment(boolean isExpanded, String message) {
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

    @Override
    public void onStart() {
        super.onStart();
    }



//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        if (isExpanded) {
//            BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
//            bottomSheetDialog.setCancelable(false);
//            bottomSheetDialog.setCanceledOnTouchOutside(false);
//            bottomSheetDialog.setOnShowListener(dialog -> {
//                FrameLayout bottomSheet = ((BottomSheetDialog) dialog).findViewById(com.google.android.material.R.id.design_bottom_sheet);
//                if (bottomSheet != null) {
//                    BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
//                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                }
//
//            });
//            return bottomSheetDialog;
//        }
//        return super.onCreateDialog(savedInstanceState);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton btnClose = view.findViewById(R.id.btn_close);
        initRecyclerViewAdapter();
        initRecyclerView(view);
    }
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
        ItemTouchHelper.Callback callback = new ItemMoveCallback(adapter);
        touchHelper  = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        initScrollListener();
        tvTitle.setText(title);
        ImageView btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        startDataListening();
    }

    protected void initRecyclerViewAdapter() {
        adapter = new DragDropItemTouchRecyclerViewAdapter(getContext(), documentInfo.getName(), documentInfo.getDocListViewDef().getList_item_layout(), new StartDragListener() {
            @Override
            public void requestDrag(RecyclerView.ViewHolder viewHolder) {
                touchHelper.startDrag(viewHolder);
            }

        });
        adapter.setFilter(filter);
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

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }
}