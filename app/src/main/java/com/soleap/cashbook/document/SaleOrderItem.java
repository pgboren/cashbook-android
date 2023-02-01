package com.soleap.cashbook.document;

import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;

import java.util.Map;

public class SaleOrderItem extends Document {

    private String itemId;
    private String itemName;
    private String itemPower;
    private String itemColor;
    private String itemYear;
    private String itemStatus;
    private double quantity;
    private double price;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPower() {
        return itemPower;
    }

    public void setItemPower(String itemPower) {
        this.itemPower = itemPower;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public String getItemYear() {
        return itemYear;
    }

    public void setItemYear(String itemYear) {
        this.itemYear = itemYear;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public Map<String, Object> toMap() {
        return  null;
    }
}
