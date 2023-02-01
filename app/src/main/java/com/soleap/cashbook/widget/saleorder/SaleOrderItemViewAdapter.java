package com.soleap.cashbook.widget.saleorder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.util.NumberUtils;
import com.soleap.cashbook.common.widget.docitems.DocItemsViewAdapter;
import com.soleap.cashbook.common.widget.docitems.DocItemsViewHolder;
import com.soleap.cashbook.document.CurrencyFactory;
import com.soleap.cashbook.document.SaleOrderItem;

import java.util.List;

public class SaleOrderItemViewAdapter extends DocItemsViewAdapter<SaleOrderItem, SaleOrderItemViewAdapter.SaleOrderItemViewAHolder> {

    public SaleOrderItemViewAdapter(List<SaleOrderItem> items, Context context) {
        super(items, context);
    }

    @Override
    protected int getItemViewResourceId() {
        return R.layout.view_input_sale_order_item_view;
    }

    @Override
    protected SaleOrderItemViewAHolder createViewHolder(View itemView) {
        return new SaleOrderItemViewAHolder(itemView);
    }

    public class SaleOrderItemViewAHolder extends DocItemsViewHolder<SaleOrderItem> {

        public SaleOrderItemViewAHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(SaleOrderItem item) {
            TextView tvItemName = itemView.findViewById(R.id.tv_item_name);
            TextView tvItemPrice = itemView.findViewById(R.id.tv_item_price);
            TextView tvItemPower = itemView.findViewById(R.id.tv_item_power);
            TextView tvItemColor = itemView.findViewById(R.id.tv_item_color);
            TextView tvItemYear = itemView.findViewById(R.id.tv_item_year);
            TextView tvItemStatus = itemView.findViewById(R.id.tv_item_status);

            tvItemName.setText(item.getItemName());
            tvItemPower.setText(item.getItemPower());
            tvItemColor.setText(item.getItemColor());
            tvItemYear.setText(item.getItemYear());
            tvItemStatus.setText(item.getItemStatus());
            tvItemPrice.setText(NumberUtils.formatDouble(CurrencyFactory.getCurrency(itemView.getContext(), CurrencyFactory.US_DOLLAR).getFormat(), item.getPrice()));
        }
    }

}