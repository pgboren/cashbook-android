package com.soleap.cashbook.view;

import java.io.Serializable;

public class ViewDef implements Serializable {

    private int viewOptionMenu = -1;
    private String title;

    private Class activityClass;

    private Class formFragmentViewClass;

    private String docName;

    private int view_layout;

    private int list_item_layout;

    private int list_item_shimmer_layout;
    private String type;

    public ViewDef(String title, String docName, Class activityClass, int view_layout, Class formFragmentViewClass) {
        this.title = title;
        this.docName = docName;
        this.activityClass = activityClass;
        this.view_layout = view_layout;
        this.formFragmentViewClass = formFragmentViewClass;
    }

    public ViewDef(String title, String docName, Class activityClass, int view_layout, Class formFragmentViewClass, int viewOptionMenu) {
        this.title = title;
        this.docName = docName;
        this.activityClass = activityClass;
        this.view_layout = view_layout;
        this.formFragmentViewClass = formFragmentViewClass;
        this.viewOptionMenu = viewOptionMenu;
    }

    public ViewDef(String title, String docName, Class activityClass, int view_layout, int list_item_layout, int layout_shimmer) {
        this.title = title;
        this.docName = docName;
        this.activityClass = activityClass;
        this.view_layout = view_layout;
        this.list_item_layout = list_item_layout;
        this.list_item_shimmer_layout = layout_shimmer;
    }

    public int getViewOptionMenu() {

        if (viewOptionMenu == -1) {
            throw new RuntimeException("Stub!");
        }

        return viewOptionMenu;
    }

    public String getDocName() {
        return docName;
    }

    public int getList_item_layout() {
        return list_item_layout;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public int getView_layout() {
        return view_layout;
    }

    public String getType() {
        return type;
    }

    public int getList_item_shimmer_layout() {
        return list_item_shimmer_layout;
    }

    public String getTitle() {
        return title;
    }

    public Class getFormFragmentViewClass() {
        return formFragmentViewClass;
    }
}
