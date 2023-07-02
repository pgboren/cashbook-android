package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.soleap.cashbook.Global;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.view.ViewType;

import org.w3c.dom.Text;

public class ItemSpecListItemViewHolder  extends DragDropListItemViewHolder {

    public ImageView imageView;

    @Override
    protected void bindViewContent(DocumentSnapshot doc) {
        super.bindViewContent(doc);
        TextView txtName = itemView.findViewById(R.id.txt_name);
        TextView txtValue = itemView.findViewById(R.id.txt_value);
        imageView = itemView.findViewById(R.id.img_drag_handler);
        txtName.setText(ResourceUtil.getStringResourceByName(itemView.getContext(), doc.getDataValue("name").getValue().toString().toLowerCase()));
        txtValue.setText(doc.getDataValue("value").getValue().toString());
    }

    public ItemSpecListItemViewHolder(Context context, View itemView) {
        super(context, itemView, ViewType.LIST_ITEM_VIEW, DocumentName.ITEM_SPECT, R.layout.list_item_spec_view);
    }
}
