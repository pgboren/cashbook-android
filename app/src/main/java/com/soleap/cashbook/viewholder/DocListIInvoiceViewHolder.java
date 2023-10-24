package com.soleap.cashbook.viewholder;

import android.content.Context;
import android.view.View;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.util.ResourceUtil;
import com.soleap.cashbook.common.value.ViewSetterFactory;
import com.soleap.cashbook.common.value.ViewType;
import com.soleap.cashbook.document.DocumentName;

import java.util.Locale;

public class DocListIInvoiceViewHolder extends DocListItemViewHolder {
    public DocListIInvoiceViewHolder(Context context, View itemView) {
        super(context, itemView, com.soleap.cashbook.view.ViewType.LIST_ITEM_VIEW, DocumentName.INVOICE, R.layout.list_item_invoice_view);
    }

    @Override
    protected void bindViewContent(Document document) {
        DocumentSnapshot doc = (DocumentSnapshot) document;
        ViewSetterFactory viewSetterFactory = ViewSetterFactory.getInstance(itemView);
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_number).setString(doc.getDataValue("number").getValue().toString());
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_date).setString(doc.getDataValue("date").getValue().toString());
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_item).setString(doc.getDataValue("vehicle").getValue().toString());
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_customer).setString(doc.getDataValue("customer").getValue().toString());
        if (doc.getDataValue("paymentoption").getValue() != null && doc.getDataValue("paymentoption").getValue().toString().equals("LOAN")) {
            itemView.findViewById(R.id.institute_view_container).setVisibility(View.VISIBLE);
            viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_institute).setString(ResourceUtil.getStringResourceByName(context, doc.getDataValue("institute").getValue().toString()));
        }
        else {
            itemView.findViewById(R.id.institute_view_container).setVisibility(View.GONE);
        }

        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_paymentoption).setString(ResourceUtil.getStringResourceByName(context, doc.getDataValue("paymentoption").getValue().toString()));
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_price).setString(doc.getDataValue("price").getValue().toString());
        Double price = (Double) doc.getDataValue("price").getValue();
        viewSetterFactory.create(ViewType.TEXTVIEW, R.id.txt_price).setCurrency(price, Locale.US);

    }

}
