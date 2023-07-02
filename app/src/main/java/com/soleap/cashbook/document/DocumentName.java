package com.soleap.cashbook.document;

import android.content.Context;

import com.soleap.cashbook.R;

public class DocumentName {

    public static final String DOCUMENT_NAME = "DOCUMENT_NAME";
    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String VEHICLE = "vehicle";
    public static final String ACCOUNT_TYPE = "accounttype" ;
    public static final String ACCOUNT = "account";
    public static final String INVOICE = "invoice";
    public static final String ITEM_SPECT = "itemspecification";

    public static String CONTACT = "contact";
    public static String CATEGORY = "category";
    public static String BRANCH = "branch";
    public static String ITEM = "item";
    public static String COLOR = "color";
    public static String INSTITUE = "institute";
    public static String SALE_ORDER = "saleorder";

    public static String AGILE_STAGE = "agilestage";
    public static String AGILE_TASK = "agiletask";

    public static DocumentName instance;

    private Context context;

    public DocumentName(Context context) {
        this.context = context;
    }

    public static DocumentName getInstance(Context context) {
        if (instance == null) {
            instance = new DocumentName(context);
        }
        return instance;
    }

    public String getListTitle(String key) {

        if (key.equals(AGILE_TASK)) {
            return context.getString(R.string.agile_task);
        }

        if (key.equals(INSTITUE)) {
            return context.getString(R.string.nav_menu_institute);
        }

        if (key.equals(SALE_ORDER)) {
            return context.getString(R.string.sale_order);
        }

        if (key.equals(COLOR)) {
            return context.getString(R.string.nav_menu_color);
        }

        if (key.equals(ITEM)) {
            return context.getString(R.string.nav_menu_item);
        }

        if (key.equals(BRANCH)) {
            return context.getString(R.string.nav_menu_branch);
        }

        if (key.equals(CATEGORY)) {
            return context.getString(R.string.nav_menu_category);
        }

        if (key.equals(CONTACT)) {
            return context.getString(R.string.nav_menu_contacts);
        }

        if (key.equals(ACCOUNT_TYPE)) {
            return context.getString(R.string.account_type);
        }

        if (key.equals(ACCOUNT)) {
            return context.getString(R.string.chart_of_accounts);
        }

        return key;
    }
    
    public int getListItemLayoutView(String key) {

//        if (key.equals(AGILE_TASK)) {
//            return R.layout.list_item_task;
//        }
//
//        if (key.equals(CONTACT)) {
//            return R.layout.list_item_contact;
//        }
//
//        if (key.equals(ITEM)) {
//            return R.layout.list_item_item;
//        }
//
//        if (key.equals(INSTITUE)) {
//            return R.layout.list_item_institute;
//        }
//
//        if (key.equals(COLOR)) {
//            return R.layout.list_item_view;
//        }
//
//        if (key.equals(BRANCH)) {
//            return R.layout.list_item_view;
//        }
//
//        if (key.equals(CATEGORY)) {
//            return R.layout.list_item_view;
//        }

        return R.layout.list_item_name_view;
    }

    public int getListActivityLayout(String key) {

        if (key.equals(BRANCH)) {
            return R.layout.activity_list_bsdoc;
        }

        if (key.equals(COLOR)) {
            return R.layout.activity_list_bsdoc;
        }

        if (key.equals(ITEM)) {
            return R.layout.activity_list_bsdoc;
        }

        if (key.equals(CATEGORY)) {
            return R.layout.activity_list_bsdoc;
        }

        if (key.equals(INSTITUE)) {
            return R.layout.activity_list_bsdoc;
        }

        if (key.equals(CONTACT)) {
            return R.layout.activity_list_bsdoc;
        }

        return R.layout.activity_list_bsdoc;
//        throw new RuntimeException("Stub!");
    }

    public Class getDocumentClass(String documentName) {

        if (documentName.equals(DocumentName.CATEGORY)) {
            return Category.class;
        }

        throw new RuntimeException("Stub!");
    }

}
