package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.document.ItemSpecification;
import com.soleap.cashbook.view.ViewType;

public class ItemSpecListItemViewHolder  extends OneToManyListItemViewHolder {

    public ImageView imageView;

    @Override
    protected void bindViewContent(Document document) {
        ItemSpecification itemSpecification = (ItemSpecification) document;
        super.bindViewContent(document);
        TextView txtName = itemView.findViewById(R.id.txt_name);
        TextView txtValue = itemView.findViewById(R.id.txt_value);
        imageView = itemView.findViewById(R.id.img_drag_handler);
        txtName.setText(itemSpecification.getName());
        txtValue.setText(itemSpecification.getValue());
    }

    public ItemSpecListItemViewHolder(Context context, View itemView) {
        super(context, itemView, ViewType.LIST_ITEM_VIEW, DocumentName.ITEM_SPECT, R.layout.list_item_spec_view);
    }
}
