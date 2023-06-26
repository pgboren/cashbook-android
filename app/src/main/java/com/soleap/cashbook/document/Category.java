package com.soleap.cashbook.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import com.soleap.cashbook.common.document.BsDocument;
import com.soleap.cashbook.common.document.Document;

import java.util.Map;

public class Category extends BsDocument {

    @SerializedName("parent")
    public String parent;

    public String color = "#F8BBD0";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = super.toMap();
        data.put("color", getColor());
        return data;
    }

    @Override
    public void fromJsonObject(JsonElement jsonElement) {
        super.fromJsonObject(jsonElement);
        JsonObject jsonObject = (JsonObject) jsonElement;
        if (jsonObject.get("color") != null) {
            setColor(jsonObject.get("color").getAsString());
        }
    }

}
