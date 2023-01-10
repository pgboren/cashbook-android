package com.soleap.cashbook.common.document;

import android.view.View;

import java.util.Map;

public class ViewData extends Document {

    public static String GROUP = "GROUP";
    public static String DATA = "DATA";
    public static String PHOTO_VIEW = "PHOTO_VIEW";
    public static String PHOTO_UPLOAD = "PHOTO_UPLOAD";

    private String label;
    private Object value;
    private String type;
    private String dataType;
    private String format;
    private String locale;
    private int visible = View.VISIBLE;
    private Map<String, ViewData> childrent;
    private Action[] actions;
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Map<String, ViewData> getChildrent() {
        return childrent;
    }

    public void setChildrent(Map<String, ViewData> childrent) {
        this.childrent = childrent;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
            this.locale = locale;
    }

    public Action[] getActions() {
        return actions;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }

    public ViewData getDataValue(String key) {
        return getChildrent().get(key);
    }

    public boolean isNullValue(String key) {
        if (getDataValue(key).getValue() != null) {
            return false;
        }
        return true;
    }

}
