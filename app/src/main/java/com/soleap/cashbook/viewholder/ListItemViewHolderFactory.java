package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;

import com.soleap.cashbook.document.DocumentName;

public class ListItemViewHolderFactory {

    public  static DocListItemViewHolder create(Context context, View itemView, String docName) {
        if (docName.equals(DocumentName.CONTACT)) {
            return new NameListItemViewHolder(context, itemView);
        }
        throw new RuntimeException("Incorrect document name");
    }

}
