package com.soleap.cashbook.common.document;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public abstract class Document implements Serializable {

    @SerializedName("_id")
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
