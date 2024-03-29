package com.soleap.cashbook.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.BsDocument;

import java.util.HashMap;
import java.util.Map;

public class ItemSpecification extends BsDocument {
    private String name;
    private String value;
    private String item;

    private int order;

    public boolean showOnInvoice = false;

    public boolean isShowOnInvoice() {
        return showOnInvoice;
    }

    public void setShowOnInvoice(boolean showOnInvoice) {
        this.showOnInvoice = showOnInvoice;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public void fromJsonObject(JsonElement jsonElement) {
        JsonObject jsonObject = (JsonObject) jsonElement;
        setName(jsonObject.get("name").getAsString());
        setValue(jsonObject.get("value").getAsString());
        setItem(jsonObject.get("item").getAsString());
        setOrder(jsonObject.get("order").getAsInt());
        setShowOnInvoice(jsonObject.get("showOnInvoice").getAsBoolean());
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("value", getValue());
        data.put("item", getItem());
        data.put("showOnInvoice", isShowOnInvoice());
        return data;
    }
}
