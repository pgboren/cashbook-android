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

    @SerializedName("paymentType")
    private String paymentType;

    @SerializedName("contact")
    private String contact;

    @SerializedName("institute")
    private String institute;

    @SerializedName("item")
    private String item;

    @SerializedName("machineNumber")
    private String machineNumber;

    @SerializedName("chassisNumber")
    private String chassisNumber;

    @SerializedName("color")
    private String color;

    @SerializedName("year")
    private int year;

    @SerializedName("condition")
    private String condition;

    @SerializedName("paymentoption")
    private String paymentoption;

    @SerializedName("qty")
    private int qty;

    @SerializedName("price")
    private float price;

    @SerializedName("plateNumber")
    private String plateNumber;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getDate() {
        return date;
    }

    public String getPaymentoption() {
        return paymentoption;
    }

    public void setPaymentoption(String paymentoption) {
        this.paymentoption = paymentoption;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("date", date);
        map.put("paymentType", paymentType);
        map.put("contact", contact);
        map.put("institute", institute);
        map.put("item", item);
        map.put("machineNumber", machineNumber);
        map.put("chassisNumber", chassisNumber);
        map.put("plateNumber", plateNumber);
        map.put("color", color);
        map.put("year", year);
        map.put("condition", condition);
        map.put("paymentoption", condition);
        map.put("qty", qty);
        map.put("price", price);
        return map;
    }

    @Override
    public void fromJsonObject(JsonElement jsonElement) {
        JsonObject jsonObject = (JsonObject) jsonElement;
        setId(jsonObject.get("_id").getAsString());
        setDate(jsonObject.get("date").getAsLong());
        setPaymentType(jsonObject.get("paymentType").getAsString());
        setContact(jsonObject.get("contact").getAsString());
        setItem(jsonObject.get("item").getAsString());
        setMachineNumber(jsonObject.get("machineNumber").getAsString());
        setChassisNumber(jsonObject.get("chassisNumber").getAsString());
        setPlateNumber(jsonObject.get("plateNumber").getAsString());
        setColor(jsonObject.get("color").getAsString());
        setYear(jsonObject.get("year").getAsInt());
        setCondition(jsonObject.get("condition").getAsString());
        setPaymentoption(jsonObject.get("paymentoption").getAsString());
        setQty(jsonObject.get("qty").getAsInt());
        setPrice(jsonObject.get("price").getAsFloat());
    }
}
