package com.soleap.cashbook.document;

import android.content.Context;

import com.soleap.cashbook.R;
import com.soleap.cashbook.activity.BsColorDocAddNewActivity;
import com.soleap.cashbook.activity.SaleInstallmentPaymentRequestAddNewActivity;
import com.soleap.cashbook.activity.DealViewActivity;
import com.soleap.cashbook.common.activity.BsColorDocEditActivity;
import com.soleap.cashbook.common.activity.BsDocAddNewActivity;
import com.soleap.cashbook.common.activity.BsDocEditActivity;
import com.soleap.cashbook.common.activity.BsDocViewActivity;
import com.soleap.cashbook.activity.BsInstituteDocAddNewActivity;
import com.soleap.cashbook.activity.BsInstituteDocEditActivity;
import com.soleap.cashbook.activity.BsItemDocAddNewActivity;
import com.soleap.cashbook.activity.BsItemDocEditActivity;
import com.soleap.cashbook.activity.ContactAddNewActivity;
import com.soleap.cashbook.activity.ContactEditActivity;
import com.soleap.cashbook.common.document.Document;

public class DocumentInfo {

    public static final String DOCUMENT_NAME = "DOCUMENT_NAME";

    public static final String VEHICLE = "vehicle";
    public static String ORDER = "order";
    public static String CONTACT = "contact";
    public static String SALE = "sale";
    public static String CATEGORY = "category";
    public static String BRANCH = "branch";
    public static String ITEM = "item";
    public static String COLOR = "color";
    public static String INSTITUE = "institute";
    public static String CUSTOMER = "customer";

    public static DocumentInfo instance;

    private Context context;

    public DocumentInfo(Context context) {
        this.context = context;
    }

    public static DocumentInfo getInstance(Context context) {
        if (instance == null) {
            instance = new DocumentInfo(context);
        }
        return instance;
    }

    public Document createDocument(String documentName) {

        if (documentName.equals(DocumentInfo.ITEM)) {
            return new Item();
        }

        if (documentName.equals(DocumentInfo.INSTITUE)) {
            return new Institute();
        }

        if (documentName.equals(DocumentInfo.COLOR)) {
            return new Color();
        }

        if (documentName.equals(DocumentInfo.BRANCH)) {
            return new Branch();
        }

        if (documentName.equals(DocumentInfo.CATEGORY)) {
            return new Category();
        }

        if (documentName.equals(DocumentInfo.CONTACT)) {
            return new Contact();
        }

        throw new RuntimeException("Stub!");
    }

    public String getListTitle(String key) {

        if (key.equals(INSTITUE)) {
            return context.getString(R.string.nav_menu_institute);
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

        if (key.equals(SALE)) {
            return context.getString(R.string.nav_menu_sale);
        }

        throw new RuntimeException("Stub!");
    }

    public Class getEditActivityClass(String key) {

        if (key.equals(BRANCH)) {
            return BsDocEditActivity.class;
        }

        if (key.equals(ITEM)) {
            return BsItemDocEditActivity.class;
        }

        if (key.equals(COLOR)) {
            return BsColorDocEditActivity.class;
        }

        if (key.equals(INSTITUE)) {
            return BsInstituteDocEditActivity.class;
        }

        if (key.equals(CATEGORY)) {
            return BsDocEditActivity.class;
        }

        if (key.equals(CONTACT)) {
            return ContactEditActivity.class;
        }

        throw new RuntimeException("Stub!");
    }

    public Class getAddNewActivityClass(String key) {
        if (key.equals(BRANCH)) {
            return BsDocAddNewActivity.class;
        }

        if (key.equals(COLOR)) {
            return BsColorDocAddNewActivity.class;
        }

        if (key.equals(ITEM)) {
            return BsItemDocAddNewActivity.class;
        }

        if (key.equals(CATEGORY)) {
            return BsDocAddNewActivity.class;
        }

        if (key.equals(INSTITUE)) {
            return BsInstituteDocAddNewActivity.class;
        }

        if (key.equals(CONTACT)) {
            return ContactAddNewActivity.class;
        }

        if (key.equals(SALE)) {
            return SaleInstallmentPaymentRequestAddNewActivity.class;
        }

        throw new RuntimeException("Stub!");
    }

    public Class getViewActivityClass(String key) {

        if (key.equals(SALE)) {
            return DealViewActivity.class;
        }

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

        throw new RuntimeException("Stub!");
    }

    public int getListItemLayout(String key) {

        if (key.equals(SALE)) {
            return R.layout.list_item_deal;
        }

        if (key.equals(CONTACT)) {
            return R.layout.list_item_contact;
        }

        if (key.equals(ITEM)) {
            return R.layout.list_item_item;
        }

        if (key.equals(INSTITUE)) {
            return R.layout.list_item_institute;
        }

        if (key.equals(COLOR)) {
            return R.layout.list_item_bsdoc;
        }

        if (key.equals(BRANCH)) {
            return R.layout.list_item_bsdoc;
        }

        if (key.equals(CATEGORY)) {
            return R.layout.list_item_bsdoc;
        }

        throw new RuntimeException("Stub!");
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

        if (key.equals(SALE)) {
            return R.layout.activity_list_bsdoc;
        }

        if (key.equals(CONTACT)) {
            return R.layout.activity_list_bsdoc;
        }

        throw new RuntimeException("Stub!");
    }

    public Class getDocumentClass(String documentName) {
        if (documentName.equals(DocumentInfo.BRANCH)) {
            return Branch.class;
        }

        if (documentName.equals(DocumentInfo.SALE)) {
            return Deal.class;
        }

        if (documentName.equals(DocumentInfo.CATEGORY)) {
            return Category.class;
        }

        throw new RuntimeException("Stub!");
    }

}
