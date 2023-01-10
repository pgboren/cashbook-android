package com.soleap.cashbook.common.document;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public abstract class Document implements Serializable {

    @SerializedName("_id")
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> toMap() {
        return  null;
    }
}
