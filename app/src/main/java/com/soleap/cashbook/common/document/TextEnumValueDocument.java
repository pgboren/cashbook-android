package com.soleap.cashbook.common.document;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

public class TextEnumValueDocument {

    private Drawable icon;

    private String key;

    private String text;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getText());
        builder.append(" (");
        builder.append(getKey());
        builder.append(")");
        return builder.toString();
    }
}
