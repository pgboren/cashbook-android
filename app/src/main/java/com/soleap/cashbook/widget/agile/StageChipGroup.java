package com.soleap.cashbook.widget.agile;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.repository.DocumentSnapshotRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.document.DocumentInfo;

import java.util.List;

public class StageChipGroup extends ChipGroup {

    private List<DocumentSnapshot> stages;
    private Chip allChip;
    private String selectedStage = null;

    public String getSelectedStage() {
        return selectedStage;
    }

    public void setSelectedStage(String selectedStage) {
        this.selectedStage = selectedStage;
    }

    public StageChipGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StageChipGroup, 0, 0);
        selectedStage = a.getString(R.styleable.StageChipGroup_selectedStage);
        builStageChips();
    }

    protected void checkAllStage(boolean check) {
        for (int i = 0; i < StageChipGroup.this.getChildCount(); i++) {
            Chip chip = (Chip)this.getChildAt(i);
            chip.setChecked(check);
        }
    }

    protected boolean isAllCheck() {
        boolean isCheck = true;
        for (int i = 1; i < StageChipGroup.this.getChildCount(); i++) {
            Chip chip = (Chip)this.getChildAt(i);
            isCheck = isCheck && chip.isChecked();
        }
        return isCheck;
    }

    private OnClickListener onAllChipCheckChange = new OnClickListener() {
        @Override
        public void onClick(View view) {
            checkAllStage(allChip.isChecked());
        }
    };

    protected void builStageChips() {
        DocumentSnapshotRepository repository = RepositoryFactory.create().get(DocumentInfo.AGILE_STAGE);
        repository.setListDocumentListner(new DocumentSnapshotRepository.OnListedDocumentListner() {
            @Override
            public void onListed(List<DocumentSnapshot> stageDocs) {
                stages = stageDocs;
                for (DocumentSnapshot stageDoc : stageDocs) {
                    Chip chip = new Chip(getContext(), null, R.attr.CustomChipChoiceStyle);
                    if (selectedStage.equals(stageDoc.getId())) {
                        chip.setChecked(true);
                    }
                    chip.setTag(stageDoc.getId());
                    chip.setText(stageDoc.getDataValue("name").getValue().toString());
                    chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                            if (isChecked) {
                                selectedStage = chip.getTag().toString();
//                                Toast toast=Toast.makeText(getContext(),selectedStage,Toast.LENGTH_SHORT);
//                                toast.setMargin(50,50);
//                                toast.show();
                            }
                        }
                    });
                    addView(chip);
                }
            }
            @Override
            public void onError(Throwable t) {
            }
        });
        repository.list();
    }
}
