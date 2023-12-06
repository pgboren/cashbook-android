package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.document.Contact;
import com.soleap.cashbook.document.DocumentName;
import com.soleap.cashbook.view.ViewType;

public class ContactListItemViewHolder extends DocListItemViewHolder {

    public ContactListItemViewHolder(Context context, View itemView) {
        super(context, itemView, ViewType.LIST_ITEM_VIEW, DocumentName.CONTACT, R.layout.list_item_contact_view);
    }

    @Override
    protected void bindViewContent(Document doc) {
        Contact contact = (Contact) doc;
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_name).setString(contact.getName());
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_code).setString(contact.getType() + " - " + contact.getNumber());
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.short_name_view).setString((contact.getName().substring(0, 1).toUpperCase()));
        viewSetterFactory.create(com.soleap.cashbook.common.value.ViewType.TEXTVIEW, R.id.txt_phonenumber).setString(contact.getPrimaryNumber());
    }

}
