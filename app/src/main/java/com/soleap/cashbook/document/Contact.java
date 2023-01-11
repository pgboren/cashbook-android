package com.soleap.cashbook.document;

import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;

import java.util.HashMap;
import java.util.Map;

public class Contact extends Document {

    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("gender")
    private String gender;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("phoneNumber1")
    private String phoneNumber1;
    @SerializedName("phoneNumber2")
    private String phoneNumber2;
    @SerializedName("phoneNumber3")
    private String phoneNumber3;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("telegram")
    private String telegram;
    @SerializedName("photo")
    private Media photo;
    @SerializedName("address")
    private Address address;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getPhoneNumber3() {
        return phoneNumber3;
    }

    public void setPhoneNumber3(String phoneNumber3) {
        this.phoneNumber3 = phoneNumber3;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("firstname", getFirstname());
        data.put("lastname", getLastname());
        data.put("gender" , getGender());
        data.put("nickname", getNickname());
        data.put("phoneNumber1", getPhoneNumber1());
        data.put("phoneNumber2",  getPhoneNumber2());
        data.put("phoneNumber3", getPhoneNumber3());
        data.put("facebook", getFacebook());
        data.put("telegram", getTelegram());
        data.put("photo", getPhoto());
        data.put("address", getAddress().toMap());
        return data;
    }
}
