package com.soleap.cashbook.view;

import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.document.Color;
import com.soleap.cashbook.document.Condition;
import com.soleap.cashbook.document.Contact;
import com.soleap.cashbook.document.Institute;
import com.soleap.cashbook.document.Invoice;
import com.soleap.cashbook.document.Item;
import com.soleap.cashbook.document.ItemSpecification;
import com.soleap.cashbook.document.Maker;
import com.soleap.cashbook.document.Model;
import com.soleap.cashbook.document.Type;
import com.soleap.cashbook.document.Vehicle;

import java.io.Serializable;

public class DocumentInfo implements Serializable {

    public static final String DOCUMENT_INFO_KEY = "DOCUMENT_INFO_KEY";

    private String name;

    private Class className;

    private ViewDef docListViewDef;

    private ViewDef docViewViewDef;

    private ViewDef docAddNewDef;

    private ViewDef docEditViewDef;

    public String getName() {
        return name;
    }

    public ViewDef getDocListViewDef() {
        return docListViewDef;
    }

    public ViewDef getDocViewViewDef() {
        return docViewViewDef;
    }

    public ViewDef getDocAddNewDef() {
        return docAddNewDef;
    }

    public ViewDef getDocEditViewDef() {
        return docEditViewDef;
    }

    public Class getClassName() {
        return className;
    }

    public DocumentInfo(String name, Class className, ViewDef docListViewDef, ViewDef docViewViewDef, ViewDef docAddNewDef, ViewDef docEditViewDef) {
        this.name = name;
        this.className = className;
        this.docListViewDef = docListViewDef;
        this.docViewViewDef = docViewViewDef;
        this.docAddNewDef = docAddNewDef;
        this.docEditViewDef = docEditViewDef;
    }

    public static DocumentInfo CONDITION = new DocumentInfo("condition", Condition.class, ViewDefs.TYPE_LIST_VIEW, ViewDefs.TYPE_VIEW, ViewDefs.TYPE_ADD_NEW_VIEW, ViewDefs.TYPE_EDIT_VIEW);

    public static DocumentInfo TYPE = new DocumentInfo("type", Type.class, ViewDefs.TYPE_LIST_VIEW, ViewDefs.TYPE_VIEW, ViewDefs.TYPE_ADD_NEW_VIEW, ViewDefs.TYPE_EDIT_VIEW);
    public static DocumentInfo MODE = new DocumentInfo("model", Model.class, ViewDefs.TYPE_LIST_VIEW, ViewDefs.TYPE_VIEW, ViewDefs.TYPE_ADD_NEW_VIEW, ViewDefs.TYPE_EDIT_VIEW);
    public static DocumentInfo MAKER = new DocumentInfo("maker", Maker.class, ViewDefs.MAKER_LIST_VIEW, ViewDefs.MAKER_VIEW, ViewDefs.MAKER_ADD_NEW_VIEW, ViewDefs.MAKER_EDIT_VIEW);
    public static DocumentInfo CATEGORY = new DocumentInfo("category", Category.class, ViewDefs.CATEGORY_LIST_VIEW, ViewDefs.CATEGORY_VIEW, ViewDefs.CATEGORY_ADD_NEW_VIEW, ViewDefs.CATEGORY_EDIT_VIEW);
    public static DocumentInfo ACCOUNTTYPE = new DocumentInfo("accounttype", null, ViewDefs.ACCOUNT_TYPE_LIST_VIEW, ViewDefs.ACCOUNT_TYPE_VIEW, ViewDefs.ACCOUNT_TYPE_NEW_VIEW, ViewDefs.ACCOUNT_TYPE_EDIT_VIEW);
    public static DocumentInfo ACCOUNT = new DocumentInfo("account", null, ViewDefs.ACCOUNT_LIST_VIEW, ViewDefs.ACCOUNT_VIEW, ViewDefs.ACCOUNT_NEW_VIEW, ViewDefs.ACCOUNT_EDIT_VIEW);

    public static DocumentInfo CONTACT = new DocumentInfo("contact" , Contact.class, ViewDefs.CONTACT_LIST_VIEW, ViewDefs.CONTACT_VIEW, ViewDefs.CONTACT_NEW_VIEW, ViewDefs.CONTACT_EDIT_VIEW);
    public static DocumentInfo ITEM = new DocumentInfo("item" , Item.class, ViewDefs.ITEM_LIST_VIEW, ViewDefs.ITEM_VIEW, ViewDefs.ITEM_NEW_VIEW, ViewDefs.ITEM_EDIT_VIEW);

    public static DocumentInfo VEHICLE = new DocumentInfo("vehicle" , Item.class, ViewDefs.VEHICLE_LIST_VIEW, ViewDefs.VEHICLE_VIEW, ViewDefs.VEHICLE_NEW_VIEW, ViewDefs.VEHICLE_EDIT_VIEW);
    public static DocumentInfo ITEM_SPEC = new DocumentInfo("itemspecification" , ItemSpecification.class, ViewDefs.ITEM_SPEC_LIST_VIEW, ViewDefs.ITEM_SPEC_VIEW, ViewDefs.ITEM_SPEC_NEW_VIEW, ViewDefs.ITEM_SPEC_EDIT_VIEW);
    public static DocumentInfo INSTITUTE = new DocumentInfo("institute" , Institute.class, ViewDefs.INSTITUTE_LIST_VIEW, ViewDefs.INSTITUTE_VIEW, ViewDefs.INSTITUTE_NEW_VIEW, ViewDefs.INSTITUTE_EDIT_VIEW);
    public static DocumentInfo INVOICE = new DocumentInfo("invoice" , Invoice.class, ViewDefs.INVOICE_LIST_VIEW, ViewDefs.INVOICE_VIEW, ViewDefs.INVOICE_NEW_VIEW, ViewDefs.INVOICE_EDIT_VIEW);
    public static DocumentInfo COLOR = new DocumentInfo("color" , Color.class, ViewDefs.COLOR_LIST_VIEW, ViewDefs.COLOR_VIEW, ViewDefs.COLOR_ADD_NEW_VIEW, ViewDefs.COLOR_EDIT_VIEW);
    public static DocumentInfo getDocumentInfo(String name) {


        if (name.equals("maker")) {
            return MAKER;
        }

        if (name.equals("type")) {
            return TYPE;
        }

        if (name.equals("condition")) {
            return CONDITION;
        }

        if (name.equals("model")) {
            return MODE;
        }

        if (name.equals("category")) {
            return CATEGORY;
        }

        if (name.equals("accounttype")) {
            return ACCOUNTTYPE;
        }

        if (name.equals("account")) {
            return ACCOUNT;
        }

        if (name.equals("contact")) {
            return CONTACT;
        }

        if (name.equals("vehicle")) {
            return VEHICLE;
        }

        if (name.equals("item")) {
            return ITEM;
        }

        if (name.equals("institute")) {
            return INSTITUTE;
        }

        if (name.equals("invoice")) {
            return INVOICE;
        }

        if (name.equals("color")) {
            return COLOR;
        }

        throw new RuntimeException("Stub!");
    }

    public static Class getDocumentClass(String name) {

        if (name.equals("category")) {
            return Category.class;
        }

        if (name.equals("accounttype")) {

        }

        if (name.equals("account")) {

        }

        if (name.equals("contact")) {
            return Contact.class;
        }

        if (name.equals("vehicle")) {
            return Vehicle.class;
        }

        if (name.equals("item")) {
            return Item.class;
        }

        if (name.equals("institute")) {
            return Institute.class;
        }

        if (name.equals("invoice")) {
            return Invoice.class;
        }

        if (name.equals("color")) {
            return Color.class;
        }

        throw new RuntimeException("Stub!");
    }


}
