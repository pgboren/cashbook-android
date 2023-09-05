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

        if (docName.equals(DocumentName.VEHICLE)) {
            return new ItemListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.ITEM)) {
            return new NameListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.ITEM_SPECT)) {
            return new ItemSpecListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.CATEGORY)) {
            return new NameListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.MAKER)) {
            return new NameListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.TYPE)) {
            return new NameListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.MODEL)) {
            return new NameListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.CONDITION)) {
            return new NameListItemViewHolder(context, itemView);
        }

        if (docName.equals(DocumentName.COLOR)) {
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
