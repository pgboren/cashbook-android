package com.soleap.cashbook.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class Contact extends Document {

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("latinname")
    private String latinname;
    @SerializedName("gender")
    private String gender;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("telegram")
    private String telegram;
    @SerializedName("photo")
    private DocumentSnapshot.Media photo;
    @SerializedName("address")
    private String address;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public DocumentSnapshot.Media getPhoto() {
        return photo;
    }

    public void setPhoto(DocumentSnapshot.Media photo) {
        this.photo = photo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getLatinname() {
        return latinname;
    }

    public void setLatinname(String latinname) {
        this.latinname = latinname;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", getName());
        data.put("latinname", getLatinname());
        data.put("gender" , getGender());
        data.put("nickname", getNickname());
        data.put("phoneNumber", getPhoneNumber());
        data.put("facebook", getFacebook());
        data.put("telegram", getTelegram());
        data.put("photo", getPhoto());
        data.put("address", getAddress());
        return data;
    }

    @Override
    public void fromJsonObject(JsonElement jsonElement) {
        JsonObject jsonObject = (JsonObject) jsonElement;
        setType(jsonObject.get("type").isJsonNull() ? "" :  jsonObject.get("type").getAsString());
        setId(jsonObject.get("_id").isJsonNull() ? "" :  jsonObject.get("_id").getAsString());
        setName(jsonObject.get("name").isJsonNull() ? "" :  jsonObject.get("name").getAsString());
        setLatinname(jsonObject.get("latinname").isJsonNull() ? "" :  jsonObject.get("latinname").getAsString());

        if (jsonObject.get("type").getAsString().equals("CUS")) {
            setGender(jsonObject.get("gender").isJsonNull() ? "" :  jsonObject.get("gender").getAsString());
            setNickname(jsonObject.get("nickname").isJsonNull() ? "" :  jsonObject.get("nickname").getAsString());
        }

        setPhoneNumber(jsonObject.get("phoneNumber").isJsonNull() ? "" :  jsonObject.get("phoneNumber").getAsString());
        setFacebook(jsonObject.get("facebook").isJsonNull() ? "" :  jsonObject.get("facebook").getAsString());
        setTelegram(jsonObject.get("telegram").isJsonNull() ? "" :  jsonObject.get("telegram").getAsString());
        setAddress(jsonObject.get("address").isJsonNull() ? "" :  jsonObject.get("address").getAsString());
    }
}
