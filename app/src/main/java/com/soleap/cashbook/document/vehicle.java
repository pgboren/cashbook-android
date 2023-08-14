package com.soleap.cashbook.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.soleap.cashbook.common.document.BsDocument;

import java.util.HashMap;
import java.util.Map;

public class vehicle extends BsDocument {

    private String barcode;
    private String name;
    private String photo;
    private String account;
    private String description;
    private double price;
    private double cost;
    private String category;
    private String make;

    private String model;
    private String type;

    private String condition;
    private String chassisno;
    private String engineno;
    private String color;
    private String horsepower;
    private int year;


    private boolean enable;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getChassisno() {
        return chassisno;
    }

    public void setChassisno(String chassisno) {
        this.chassisno = chassisno;
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(String horsepower) {
        this.horsepower = horsepower;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean isEnable() {
        return enable;
    }

    @Override
    public void setEnable(boolean enable) {
        this.enable = enable;
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
        data.put("make", make);
        data.put("chassisno", chassisno);
        data.put("engineno", engineno);
        data.put("color", color);
        data.put("horsepower", horsepower);
        data.put("year", year);
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
        setMake(jsonObject.get("make").getAsString());
        setChassisno(jsonObject.get("chassisno").getAsString());
        setEngineno(jsonObject.get("engineno").getAsString());
        setColor(jsonObject.get("color").getAsString());
        setHorsepower(jsonObject.get("horsepower").getAsString());
        setYear(jsonObject.get("year").getAsInt());
        setCategory(jsonObject.get("category").getAsString());
    }
}
