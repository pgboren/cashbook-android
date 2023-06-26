package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;

import com.soleap.cashbook.document.DocumentName;

public class ListItemViewHolderFactory {

    public  static DocListItemViewHolder create(Context context, View itemView, String docName) {
        if (docName.equals(DocumentName.CONTACT)) {
            return new ContactListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.ACCOUNT)) {
            return new AccountListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.ACCOUNT_TYPE)) {
            return new NameListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.ITEM)) {
            return new ItemListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.CATEGORY)) {
            return new NameListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.INSTITUE)) {
            return new InstituteListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.INVOICE)) {
            return new DocListIInvoiceViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.COLOR)) {
            return new NameListItemViewHolder(context, itemView);
        }

        throw new RuntimeException("Incorrect document name");
    }

}
