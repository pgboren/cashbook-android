package com.soleap.cashbook.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.soleap.cashbook.common.document.BsDocument;

import java.util.HashMap;
import java.util.Map;

public class Item extends BsDocument {

    private String barcode;

    private String type;

    private String description;
    private double price;
    private double cost;
    private String category;
    private String account;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("barcode", getBarcode());
        data.put("name", getName());
        data.put("account", getAccount());
        data.put("description", getDescription());
        data.put("price",getPrice());
        data.put("cost", getCost());
        data.put("category", getCategory());
        data.put("enable", enable);
        return data;
    }

    @Override
    public void fromJsonObject(JsonElement jsonElement) {
        super.fromJsonObject(jsonElement);
        JsonObject jsonObject = (JsonObject) jsonElement;
        setBarcode(jsonObject.get("barcode").getAsString());
        setAccount(jsonObject.get("account").getAsString());
        setDescription(jsonObject.has("description") ? jsonObject.get("description").getAsString() : "");
        setPrice(jsonObject.get("price").getAsDouble());
        setCost(jsonObject.get("cost").getAsDouble());
        setCategory(jsonObject.get("category").getAsString());
    }
}
