package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;

public class OneToManyListItemViewHolder extends DocListItemViewHolder {

    public ImageView imageView;
    public ImageButton btnRemove;
    public ImageButton btnEdit;

    public View rowView;

    @Override
    protected void bindViewContent(Document doc) {
        rowView = itemView;
        imageView = itemView.findViewById(R.id.img_drag_handler);
        btnRemove = itemView.findViewById(R.id.btn_remove);
        btnEdit = itemView.findViewById(R.id.btn_edit);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDelete(doc, getAdapterPosition());
                }
            }
        });
    }

    public OneToManyListItemViewHolder(Context context, View itemView, String viewName, String docName, int resourceFileLayout) {
        super(context, itemView, viewName, docName, resourceFileLayout);
    }

}
