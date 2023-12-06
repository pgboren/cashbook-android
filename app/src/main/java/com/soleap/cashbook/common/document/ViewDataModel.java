package com.soleap.cashbook.common.document;

import java.util.List;

public class ViewDataModel extends Document {
    private String className;
    private String menu;
    private String title;
    private List<ViewDataGroup> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ViewDataGroup> getData() {
        return data;
    }

    public void setData(List<ViewDataGroup> data) {
        this.data = data;
    }

    public void onCreated(String id) {
        this.set_id(id);
    }

    public void onUpdated() {

    }

    public void onDeleted() {

    }

    public interface EventListener {
        public void onAdded(String id);
        public void onChanged(String id);
        public void onRemoved(String id);
    }

}
