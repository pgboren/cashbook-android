package com.soleap.cashbook.document;

import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.BsDocument;

import java.util.Map;


public class Institute extends BsDocument {

    @SerializedName("address")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = super.toMap();
        data.put("address", getAddress().toMap());
        return data;
    }
}
