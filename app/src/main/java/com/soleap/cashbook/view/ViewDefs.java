package com.soleap.cashbook.view;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.BsDocAddNewActivity;
import com.soleap.cashbook.common.activity.BsDocEditActivity;
import com.soleap.cashbook.common.activity.BsDocListActivity;
import com.soleap.cashbook.common.activity.BsDocViewActivity;
import com.soleap.cashbook.common.fragment.BSDocFormFragment;
import com.soleap.cashbook.common.fragment.ContactFormFragment;
import com.soleap.cashbook.common.fragment.InstituteFormFragment;
import com.soleap.cashbook.common.fragment.InvoiceFormFragment;
import com.soleap.cashbook.common.fragment.ItemFormFragment;
import com.soleap.cashbook.document.DocumentName;

public class ViewDefs {

    public static ViewDef CATEGORY_LIST_VIEW = new ViewDef("CATEGORY_LIST_VIEW", "category", BsDocListActivity.class, R.layout.activity_list_bsdoc, R.layout.list_item_name_view, R.layout.list_item_name_shimmer_view);
    public static ViewDef CATEGORY_VIEW = new ViewDef("CATEGORY_VIEW",  "category", BsDocViewActivity.class, R.layout.activity_view_doc, BSDocFormFragment.class, R.menu.doc_view_activity_option_menu);
    public static ViewDef CATEGORY_ADD_NEW_VIEW = new ViewDef("CATEGORY_ADD_NEW_VIEW", "category", BsDocAddNewActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);
    public static ViewDef CATEGORY_EDIT_VIEW = new ViewDef("CATEGORY_EDIT_VIEW", "category", BsDocEditActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);

    public static ViewDef ACCOUNT_TYPE_LIST_VIEW = new ViewDef("ACCOUNT_TYPE_LIST_VIEW", "accounttype", BsDocListActivity.class, R.layout.activity_list_bsdoc, R.layout.list_item_name_view, R.layout.list_item_name_shimmer_view);
    public static ViewDef ACCOUNT_TYPE_VIEW = new ViewDef("ACCOUNT_TYPE_VIEW", "accounttype", BsDocViewActivity.class, R.layout.activity_view_doc, BSDocFormFragment.class);
    public static ViewDef ACCOUNT_TYPE_NEW_VIEW = new ViewDef("ACCOUNT_TYPE_NEW_VIEW", "accounttype", BsDocAddNewActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);
    public static ViewDef ACCOUNT_TYPE_EDIT_VIEW = new ViewDef("ACCOUNT_TYPE_EDIT_VIEW", "accounttype", BsDocEditActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);


    public static ViewDef ACCOUNT_LIST_VIEW = new ViewDef("ACCOUNT_LIST_VIEW", "account", BsDocListActivity.class, R.layout.activity_list_bsdoc, R.layout.list_item_account_view, R.layout.list_item_account_shimmer_view);
    public static ViewDef ACCOUNT_VIEW = new ViewDef("ACCOUNT_VIEW", "account", BsDocViewActivity.class, R.layout.activity_view_doc, BSDocFormFragment.class, R.menu.doc_view_activity_option_menu);
    public static ViewDef ACCOUNT_NEW_VIEW = new ViewDef("ACCOUNT_NEW_VIEW", "account", BsDocAddNewActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);
    public static ViewDef ACCOUNT_EDIT_VIEW = new ViewDef("ACCOUNT_EDIT_VIEW", "account", BsDocEditActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);

    public static ViewDef CONTACT_LIST_VIEW = new ViewDef("CONTACT_LIST_VIEW", "contact", BsDocListActivity.class, R.layout.activity_list_bsdoc, R.layout.list_item_contact_view, R.layout.list_item_contact_shimmer_view);
    public static ViewDef CONTACT_VIEW = new ViewDef("CONTACT_VIEW", "contact", BsDocViewActivity.class, R.layout.activity_view_doc, BSDocFormFragment.class, R.menu.doc_view_activity_option_menu);
    public static ViewDef CONTACT_NEW_VIEW = new ViewDef("CONTACT_NEW_VIEW", "contact", BsDocAddNewActivity.class, R.layout.activity_form_bsdoc, ContactFormFragment.class);
    public static ViewDef CONTACT_EDIT_VIEW = new ViewDef("CONTACT_EDIT_VIEW", "contact", BsDocEditActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);

    public static ViewDef ITEM_LIST_VIEW = new ViewDef("ITEM_LIST_VIEW", "item", BsDocListActivity.class, R.layout.activity_list_bsdoc, R.layout.list_item_item_view, R.layout.list_item_item_shimmer_view);
    public static ViewDef ITEM_VIEW = new ViewDef("ITEM_VIEW", "item", BsDocViewActivity.class, R.layout.activity_view_doc, BSDocFormFragment.class, R.menu.doc_view_activity_option_menu);
    public static ViewDef ITEM_NEW_VIEW = new ViewDef("ITEM_NEW_VIEW", "item", BsDocAddNewActivity.class, R.layout.activity_form_bsdoc, ItemFormFragment.class);
    public static ViewDef ITEM_EDIT_VIEW = new ViewDef("ITEM_EDIT_VIEW", "item", BsDocEditActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);

    public static ViewDef INSTITUTE_LIST_VIEW = new ViewDef("INSTITUTE_LIST_VIEW", "institute", BsDocListActivity.class, R.layout.activity_list_bsdoc, R.layout.list_item_institute_view, R.layout.list_item_institute_shimmer_view);
    public static ViewDef INSTITUTE_VIEW = new ViewDef("INSTITUTE_VIEW", "institute", BsDocViewActivity.class, R.layout.activity_view_doc, BSDocFormFragment.class, R.menu.doc_view_activity_option_menu);
    public static ViewDef INSTITUTE_NEW_VIEW = new ViewDef("INSTITUTE_NEW_VIEW", "institute", BsDocAddNewActivity.class, R.layout.activity_form_bsdoc, InstituteFormFragment.class);
    public static ViewDef INSTITUTE_EDIT_VIEW = new ViewDef("INSTITUTE_EDIT_VIEW", "institute", BsDocEditActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);

    public static ViewDef INVOICE_LIST_VIEW = new ViewDef("INVOICE_LIST_VIEW", "INVOICE", BsDocListActivity.class, R.layout.activity_list_bsdoc, R.layout.list_item_invoice_view, R.layout.list_item_invoice_shimmer_view);

    public static ViewDef INVOICE_VIEW = new ViewDef("INVOICE_VIEW", "INVOICE", BsDocViewActivity.class, R.layout.activity_view_doc, BSDocFormFragment.class, R.menu.invoice_view_activity_option_menu);

    public static ViewDef INVOICE_NEW_VIEW = new ViewDef("INVOICE_NEW_VIEW", "INVOICE", BsDocAddNewActivity.class, R.layout.activity_form_bsdoc, InvoiceFormFragment.class);

    public static ViewDef INVOICE_EDIT_VIEW = new ViewDef("INVOICE_EDIT_VIEW", "INVOICE", BsDocEditActivity.class, R.layout.activity_form_bsdoc, null);

    public static ViewDef COLOR_LIST_VIEW = new ViewDef("COLOR_LIST_VIEW", "color", BsDocListActivity.class, R.layout.activity_list_bsdoc, R.layout.list_item_name_view, R.layout.list_item_name_shimmer_view);

    public static ViewDef COLOR_VIEW = new ViewDef("COLOR_VIEW",  "color", BsDocViewActivity.class, R.layout.activity_view_doc, BSDocFormFragment.class, R.menu.doc_view_activity_option_menu);

    public static ViewDef COLOR_ADD_NEW_VIEW = new ViewDef("COLOR_ADD_NEW_VIEW", "color", BsDocAddNewActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);
    public static ViewDef COLOR_EDIT_VIEW = new ViewDef("COLOR_EDIT_VIEW", "color", BsDocEditActivity.class, R.layout.activity_form_bsdoc, BSDocFormFragment.class);

}
