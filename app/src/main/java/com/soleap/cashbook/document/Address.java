package com.soleap.cashbook.document;

import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;

public class Address extends Document {

    @SerializedName("houseNo")
    private String houseNo;

    @SerializedName("floor")
    private String floor;

    @SerializedName("roomNumber")
    private String roomNumber;

    @SerializedName("postalCode")
    private String postalCode;

    @SerializedName("street")
    private String street;

    @SerializedName("village")
    private String village;

    @SerializedName("commune")
    private String commune;

    @SerializedName("district")
    private String district;

    @SerializedName("province")
    private String province;

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
