package com.soleap.cashbook.viewholder;

import android.view.View;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;

public interface ListItemViewHolder {
    String getViewName();
    String getDocName();

    int getResourceFileLayout();

}
