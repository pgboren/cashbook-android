package com.soleap.cashbook.widget.saleorder;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.widget.docitems.DocItemsView;
import com.soleap.cashbook.common.widget.docitems.DocItemsViewAdapter;
import com.soleap.cashbook.document.SaleOrderItem;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderItemsView extends DocItemsView {

    public SaleOrderItemsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected DocItemsViewAdapter createAdapter(Context context) {
        List<SaleOrderItem> data = new ArrayList<SaleOrderItem>();
        SaleOrderItem item = new SaleOrderItem();
        item.setItemName("ហុងដាឌ្រីម 125CC ខ្មៅ ឆ្នាំ 2023");
        item.setItemColor("ខ្មៅ");
        item.setItemPower("125CC");
        item.setItemYear("2023");
        item.setItemStatus(context.getString(R.string.NEW));
        item.setPrice(2750);
        data.add(item);
        return new SaleOrderItemViewAdapter(data, context);
    }




}
