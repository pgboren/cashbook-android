package com.soleap.cashbook.document;

import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;

public class Customer extends Document {

    public static String COLLECTION_NAME = "customers";

    @SerializedName("name")
    private String name;
    @SerializedName("latinName")
    private String latinName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("idcardNo")
    private String idcardNo;
    @SerializedName("idcardPhoto")
    private String idcardPhoto;
    @SerializedName("otherDocumentPhoto")
    private String otherDocumentPhoto;
    @SerializedName("address")
    private String address;
    @SerializedName("phoneNumber1")
    private String phoneNumber1;
    @SerializedName("phoneNumber2")
    private String phoneNumber2;
    @SerializedName("email")
    private String email;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("photo")
    private Media photo;

    public Media getPhoto() {
        return photo;
    }

    public void setPhoto(Media photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getIdcardPhoto() {
        return idcardPhoto;
    }

    public void setIdcardPhoto(String idcardPhoto) {
        this.idcardPhoto = idcardPhoto;
    }

    public String getOtherDocumentPhoto() {
        return otherDocumentPhoto;
    }

    public void setOtherDocumentPhoto(String otherDocumentPhoto) {
        this.otherDocumentPhoto = otherDocumentPhoto;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

}
