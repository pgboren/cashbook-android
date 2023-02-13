package com.soleap.cashbook.common.widget.agile;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.viewholder.BsDocViewHolder;

public class AgileStageSummaryItemViewHolder extends BsDocViewHolder {


    public AgileStageSummaryItemViewHolder(Activity activity, String documentName) {
        super(activity, documentName);
    }

    public void bind(View itemView, final int position, final DocumentSnapshot doc) {
        TextView txtStageName = itemView.findViewById(R.id.txt_stage_name);
        ImageView imvStageIcon = itemView.findViewById(R.id.imv_stage_icon);
        imvStageIcon.setImageDrawable(ResourceUtil.getDrawableResourceByName(this.activity, doc.getDataValue("icon").getValue().toString()));
        txtStageName.setText(doc.getDataValue("name").getValue().toString());
        itemView.setBackgroundColor(Color.parseColor(doc.getDataValue("color").getValue().toString()));
    }
}
