package com.soleap.cashbook.document;

import android.content.Context;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocAddNewActivity;
import com.soleap.cashbook.common.activity.BsDocEditActivity;
import com.soleap.cashbook.common.activity.BsDocViewActivity;
import com.soleap.cashbook.common.document.Document;

public class DocumentName {

    public static final String DOCUMENT_NAME = "DOCUMENT_NAME";
    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String VEHICLE = "vehicle";
    public static final String ACCOUNT_TYPE = "accounttype" ;
    public static String ORDER = "order";
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

    public Document createDocument(String documentName) {

        if (documentName.equals(DocumentName.CATEGORY)) {
            return new Category();
        }

        throw new RuntimeException("Stub!");
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

        return key;
    }

    public Class getEditActivityClass(String key) {

        if (key.equals(BRANCH)) {
            return BsDocEditActivity.class;
        }

        if (key.equals(CATEGORY)) {
            return BsDocEditActivity.class;
        }

        throw new RuntimeException("Stub!");
    }

    public Class getAddNewActivityClass(String key) {

        if (key.equals(BRANCH)) {
            return BsDocAddNewActivity.class;
        }

        if (key.equals(CATEGORY)) {
            return BsDocAddNewActivity.class;
        }

        return null;
    }

    public Class getViewActivityClass(String key) {

        if (key.equals(BRANCH)) {
            return BsDocViewActivity.class;
        }

        if (key.equals(COLOR)) {
            return BsDocViewActivity.class;
        }

        if (key.equals(ITEM)) {
            return BsDocViewActivity.class;
        }

        if (key.equals(CATEGORY)) {
            return BsDocViewActivity.class;
        }

        if (key.equals(INSTITUE)) {
            return BsDocViewActivity.class;
        }

        if (key.equals(CONTACT)) {
            return BsDocViewActivity.class;
        }

        return null;
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
