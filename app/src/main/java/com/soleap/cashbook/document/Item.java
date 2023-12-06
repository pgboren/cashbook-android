package com.soleap.cashbook.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.soleap.cashbook.common.document.BsDocument;
import com.soleap.cashbook.common.document.RefDocument;

import java.util.HashMap;
import java.util.Map;

public class Item extends BsDocument {

    private String category;
    private double price;
    private double cost;


    private String maker;
    private String type;
    private String condition;
    private String color;
    private String model;
    private String chassisNo;
    private String engineNo;
    private String horsepower;
    private int year;

    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public String getAccount() {
//        return account;
//    }
//
//    public void setAccount(String account) {
//        this.account = account;
//    }

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

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
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
    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("category", getCategory());
        data.put("description", getDescription());
        data.put("price",getPrice());
        data.put("cost", getCost());
        data.put("maker", maker);
        data.put("type", type);
        data.put("condition", condition);
        data.put("color", color);
        data.put("model", model);
        data.put("chassisno", chassisNo);
        data.put("engineno", engineNo);
        data.put("horsepower", horsepower);
        data.put("year", year);
        data.put("enable", enable);

        return data;
    }

    @Override
    public void fromJsonObject(JsonElement jsonElement) {
        super.fromJsonObject(jsonElement);
        JsonObject jsonObject = (JsonObject) jsonElement;
        setName(jsonObject.get("name").getAsString());
        JsonObject categoryObject = jsonObject.get("category").getAsJsonObject();
        setDescription(jsonObject.has("description") ? jsonObject.get("description").getAsString() : "");
        setPrice(jsonObject.get("price").getAsDouble());
        setCost(jsonObject.get("cost").getAsDouble());
        setMaker(jsonObject.get("maker").getAsString());
        setType(jsonObject.get("type").getAsString());
        setCondition(jsonObject.get("condition").getAsString());
        setColor(jsonObject.get("color").getAsString());
        setModel(jsonObject.get("model").getAsString());
        setChassisNo(jsonObject.get("chassisno").getAsString());
        setEngineNo(jsonObject.get("engineno").getAsString());
        setHorsepower(jsonObject.get("horsepower").getAsString());
        setYear(jsonObject.get("year").getAsInt());
        setEnable(jsonObject.get("enable").getAsBoolean());
    }
}
