package com.soleap.cashbook.widget.agile;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;

public class AgileStageButtomSheetViewHolder extends DocListItemViewHolder {
    public AgileStageButtomSheetViewHolder(Context activity, View itemView, String viewName, String docName, int resourceFileLayout) {
        super(activity, itemView, viewName, docName, resourceFileLayout);
    }

//    public AgileStageButtomSheetViewHolder(Context activity, String viewName, String docName) {
//        super(activity, viewName, docName);
//    }

    public void bind(View itemView, final int position, final DocumentSnapshot doc) {
        View colorView = itemView.findViewById(R.id.view_color);
        colorView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(doc.getDataValue("color").getValue().toString())));
        TextView textTitle = itemView.findViewById(R.id.text);
        textTitle.setText(doc.getDataValue("name").getValue().toString());
    }
}
