package com.soleap.cashbook.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.Media;
import java.util.Map;

public class Institute extends Document {
    private String code;

    private String name;

    private String latinname;

    private String logo;

    private Media logoMedia;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    private String address;

    public String getLatinname() {
        return latinname;
    }

    public void setLatinname(String latinname) {
        this.latinname = latinname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Media getLogoMedia() {
        return logoMedia;
    }

    public void setLogoMedia(Media logoMedia) {
        this.logoMedia = logoMedia;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = super.toMap();
        data.put("address", getAddress());
        return data;
    }

    @Override
    public void fromJsonObject(JsonElement jsonElement) {
        JsonObject jsonObject = (JsonObject) jsonElement;
        set_id(jsonObject.get("_id").getAsString());
        setName(jsonObject.get("name").getAsString());
        setLatinname(jsonObject.get("latinname").getAsString());
        setCode(jsonObject.get("code").getAsString());
        setAddress(jsonObject.get("address").getAsString());
        if (!jsonObject.get("logo").isJsonNull()) {
            JsonObject logo = jsonObject.get("logo").getAsJsonObject();
            setLogo(logo.get("_id").getAsString());
            Media logoMedia = new Media();
            logoMedia.fromJsonObject(logo);
            setLogoMedia(logoMedia);
        }
    }
}
