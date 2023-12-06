package com.soleap.cashbook.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.Media;

import java.util.HashMap;
import java.util.Map;

public class Contact extends Document {
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;
    @SerializedName("primaryNumber")
    private String primaryNumber;
    @SerializedName("secondaryNumber")
    private String secondaryNumber;
    @SerializedName("address")
    private String address;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("telegram")
    private String telegram;

    @SerializedName("number")
    private int number;
    @SerializedName("photo")
    private Media photo;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public Media getPhoto() {
        return photo;
    }

    public void setPhoto(Media photo) {
        this.photo = photo;
    }

    public String getPrimaryNumber() {
        return primaryNumber;
    }

    public void setPrimaryNumber(String primaryNumber) {
        this.primaryNumber = primaryNumber;
    }

    public String getSecondaryNumber() {
        return secondaryNumber;
    }

    public void setSecondaryNumber(String secondaryNumber) {
        this.secondaryNumber = secondaryNumber;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("type", getType());
        data.put("primaryNumber", getPrimaryNumber());
        data.put("secondaryNumber", getSecondaryNumber());
        data.put("facebook", getFacebook());
        data.put("telegram", getTelegram());
        data.put("photo", getPhoto());
        data.put("address", getAddress());
        return data;
    }

    @Override
    public void fromJsonObject(JsonElement jsonElement) {
        JsonObject jsonObject = (JsonObject) jsonElement;
//        setType(jsonObject.get("type").isJsonNull() ? "" :  jsonObject.get("type").getAsString());
//        setNumber(jsonObject.get("number").getAsInt());
        set_id(jsonObject.get("_id").isJsonNull() ? "" :  jsonObject.get("_id").getAsString());
        setName(jsonObject.get("name").isJsonNull() ? "" :  jsonObject.get("name").getAsString());
        setPrimaryNumber(jsonObject.get("primaryNumber").isJsonNull() ? "" :  jsonObject.get("primaryNumber").getAsString());
        setFacebook(jsonObject.get("facebook").isJsonNull() ? "" :  jsonObject.get("facebook").getAsString());
        setTelegram(jsonObject.get("telegram").isJsonNull() ? "" :  jsonObject.get("telegram").getAsString());

        setAddress(jsonObject.get("address").isJsonNull() ? "" :  jsonObject.get("address").getAsString());
    }
}
