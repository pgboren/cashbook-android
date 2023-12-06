package com.soleap.cashbook.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;

import java.util.HashMap;
import java.util.Map;

public class Invoice extends Document {

    @SerializedName("number")
    private int number;

    @SerializedName("date")
    private long date;

    @SerializedName("paymentoption")
    private String paymentoption;

    @SerializedName("customer")
    private String customer;

    @SerializedName("institute")
    private String institute;

    @SerializedName("vehicle")
    private String vehicle;
    @SerializedName("qty")
    private int qty;

    @SerializedName("price")
    private float price;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPaymentoption() {
        return paymentoption;
    }

    public void setPaymentoption(String paymentoption) {
        this.paymentoption = paymentoption;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("date", date);
        map.put("paymentoption", paymentoption);
        map.put("customer", customer);
        map.put("institute", institute);
        map.put("vehicle", vehicle);
        map.put("qty", qty);
        map.put("price", price);
        return map;
    }

    @Override
    public void fromJsonObject(JsonElement jsonElement) {
        JsonObject jsonObject = (JsonObject) jsonElement;
        set_id(jsonObject.get("_id").getAsString());
        setDate(jsonObject.get("date").getAsLong());
        setPaymentoption(jsonObject.get("paymentoption").getAsString());
        setCustomer(jsonObject.get("customer").getAsString());
        setVehicle(jsonObject.get("vehicle").getAsString());
        setQty(jsonObject.get("qty").getAsInt());
        setPrice(jsonObject.get("price").getAsFloat());
    }
}
