package com.soleap.cashbook.common.widget.bottomsheetmenu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String title;
    private List<MenuItem> items = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void addItem(String id, String title, Object data) {
        MenuItem item = new MenuItem();
        item.setId(id);
        item.setTitle(title);
        item.setData(data);
        items.add(item);
    }

    public void addItem(String id, String title) {
        addItem(id, title, null);
    }

    public MenuItem getItem(int position) {
        return items.get(position);
    }

    public int size() {
        return  items.size();
    }
}
