package com.soleap.cashbook.widget.agile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

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

    private OnStageValueChangedListner stageValueChangedListner;

    private List<DocumentSnapshot> stages;
    private Chip allChip;

    public void setStageValueChangedListner(OnStageValueChangedListner stageValueChangedListner) {
        this.stageValueChangedListner = stageValueChangedListner;
    }

    public void checkChipByStage(String stage, boolean check) {
        for (int i = 0; i < StageChipGroup.this.getChildCount(); i++) {
            Chip chip = (Chip)this.getChildAt(i);
            if (chip.getTag().toString().equals(stage)) {
                chip.setChecked(check);
            }
        }
    }

    public void checkChip(int position) {
        Chip chip = (Chip) getChildAt(position);
        chip.setChecked(true);
    }

    public StageChipGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setSingleSelection(true);
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

    public void setChipEnable(boolean enabled) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(enabled);
        }
    }

    protected void builStageChips() {
        DocumentSnapshotRepository repository = RepositoryFactory.create().get(DocumentInfo.AGILE_STAGE);
        repository.setListDocumentListner(new DocumentSnapshotRepository.OnListedDocumentListner() {
            @Override
            public void onListed(List<DocumentSnapshot> stageDocs) {
                stages = stageDocs;
                for (DocumentSnapshot stageDoc : stageDocs) {
                    Chip chip = new Chip(getContext(), null, R.attr.CustomChipChoiceStyle);
                    chip.setTag(stageDoc.getId());

                    chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor(stageDoc.getDataValue("color").getValue().toString())));
                    chip.setText(stageDoc.getDataValue("name").getValue().toString());
                    chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                            Log.i("test", chip.getTag().toString());
                            if (isChecked) {
                                stageValueChangedListner.OnChange(chip.getTag().toString());
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

    public interface OnStageValueChangedListner {
        void OnChange(String value);
    }
}
