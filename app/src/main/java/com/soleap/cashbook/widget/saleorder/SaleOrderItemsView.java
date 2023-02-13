package com.soleap.cashbook.widget.saleorder;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.widget.docitems.DocItemsView;

import java.util.ArrayList;

public class SaleOrderItemsView extends DocItemsView {

    public static final String DOC = "DOC";

    public SaleOrderItemsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected SaleOrderItemViewAdapter createAdapter(Context context) {
        dataSet = new ArrayList<Document>();
        return new SaleOrderItemViewAdapter(dataSet, context);
    }

}
