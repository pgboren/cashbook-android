package com.soleap.cashbook.widget.agile;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.adapter.RecyclerViewAdapter;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AgileStageBottomSheetDialog extends BottomSheetDialogFragment {

    private BottomSheetBehavior bottomSheetBehavior;
    private Dialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.agile_stage_bottom_sheet,
                container, false);
        initRecyclerView(rootView);
        return rootView;
    }

    protected void initRecyclerView(View rootView) {
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), DocumentName.AGILE_STAGE, R.layout.stage_menu_item) {
//            @Override
//            protected ViewHolder createItemViewHolder(View itemView) {
//                return new ViewHolder(itemView) {
//                    @Override
//                    protected void bind( int position, DocumentSnapshot data) {
//                        bindListItemViewHolder(this.itemView, position, data);
//                    }
//                };
//            }
//        };
//
//        RecyclerView rvStageView = rootView.findViewById(R.id.stage_recycler_view);
//        rvStageView.setHasFixedSize(true);
//        rvStageView.setAdapter(adapter);
//        rvStageView.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter.startListening();

    }

    protected void buildStageButtomMenu() {
        DocumentSnapshotRepository repository = RepositoryFactory.create().get(DocumentName.AGILE_STAGE);
        repository.setListDocumentListner(new DocumentSnapshotRepository.OnListedDocumentListner() {
            @Override
            public void onListed(List<Document> stageDocs) {
                List<StageMenuItem> menuItems = new ArrayList<>();
                for (Document doc : stageDocs) {
                    DocumentSnapshot stageDoc= (DocumentSnapshot)doc;
                    StageMenuItem  item = new StageMenuItem();
                    item.setId(stageDoc.getId());
                    item.setTitle(stageDoc.getDataValue("name").getValue().toString());
                    item.setData(stageDoc);
                    menuItems.add(item);
                }
                StageMenuItem item = new StageMenuItem();
                item.setId("ALL");
                item.setTitle(getString(R.string.all_records));
                menuItems.add(item);
            }
            @Override
            public void onError(Throwable t) {
            }
        });
//        repository.list();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomSheetBehavior = BottomSheetBehavior.from((View)view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        LinearLayout bottomSheetLayout = dialog.findViewById(R.id.bottomSheetLayout);
        assert bottomSheetLayout != null;
        bottomSheetLayout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);

    }
}
