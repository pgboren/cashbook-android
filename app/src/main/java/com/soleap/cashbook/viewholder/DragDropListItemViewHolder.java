package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.view.ViewType;

public class DragDropListItemViewHolder extends DocListItemViewHolder {

    public ImageView imageView;

    public View rowView;

    @Override
    protected void bindViewContent(DocumentSnapshot doc) {
        rowView = itemView;
        imageView = itemView.findViewById(R.id.img_drag_handler);
    }

    public DragDropListItemViewHolder(Context context, View itemView, String viewName, String docName, int resourceFileLayout) {
        super(context, itemView, viewName, docName, resourceFileLayout);
    }

}
