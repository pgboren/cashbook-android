package com.soleap.cashbook.widget.agile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.viewholder.DocListItemViewHolder;

import java.util.Map;

public class AgileStageSummaryItemViewHolder extends DocListItemViewHolder {
    @Override
    protected void bindViewContent(DocumentSnapshot doc) {

    }

    public AgileStageSummaryItemViewHolder(Context activity, View itemView, String viewName, String docName, int resourceFileLayout) {
        super(activity, itemView, viewName, docName, resourceFileLayout);
    }


    @SuppressLint("LongLogTag")
    public void bind(View itemView, final int position, final DocumentSnapshot doc) {
        try
        {
            TextView txtStageName = itemView.findViewById(R.id.txt_stage_name);
            ImageView imvStageIcon = itemView.findViewById(R.id.imv_stage_icon);
            imvStageIcon.setImageDrawable(ResourceUtil.getDrawableResourceByName(this.context, doc.getDataValue("icon").getValue().toString()));
            txtStageName.setText(doc.getDataValue("name").getValue().toString());
            itemView.setBackgroundColor(Color.parseColor(doc.getDataValue("color").getValue().toString()));
        }
        catch (Exception ex) {
            Log.e("AgileStageSummaryItemViewHolder", doc.getId());
        }

    }
}
