package com.soleap.cashbook.widget.agile;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.viewholder.BsDocViewHolder;

public class AgileStageButtomSheetViewHolder extends BsDocViewHolder {


    public AgileStageButtomSheetViewHolder(Activity activity, String documentName) {
        super(activity, documentName);
    }

    public void bind(View itemView, final int position, final DocumentSnapshot doc) {
        View colorView = itemView.findViewById(R.id.view_color);
        colorView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(doc.getDataValue("color").getValue().toString())));
        TextView textTitle = itemView.findViewById(R.id.text);
        textTitle.setText(doc.getDataValue("name").getValue().toString());
    }
}
