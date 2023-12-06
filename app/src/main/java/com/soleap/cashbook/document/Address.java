package com.soleap.cashbook.document;

import com.google.gson.JsonElement;
import com.soleap.cashbook.common.document.Document;

import java.util.HashMap;
import java.util.Map;

public class Address extends Document {

    private String houseNo;

    private String street;

    private String province;

    private String district;

    private String commune;

    private String village;

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    @Override
    public void fromJsonObject(JsonElement jsonElement) {

    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("houseNo", getHouseNo());
        data.put("street", getStreet());
        data.put("village", getVillage());
        data.put("commune", getCommune());
        return data;
    }
}
