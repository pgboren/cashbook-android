package com.soleap.cashbook.viewholder.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.soleap.cashbook.common.widget.recyclerview.OneToManyDocRecyclerInputView;
import com.soleap.cashbook.document.ItemSpecification;
import com.soleap.cashbook.view.DocumentInfo;

public class ItemSpecificationRecyclerInputView extends OneToManyDocRecyclerInputView<ItemSpecification> {

    public ItemSpecificationRecyclerInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, DocumentInfo.ITEM_SPEC);
    }

}
