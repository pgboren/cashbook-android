package com.soleap.cashbook.common.document;

import com.google.gson.annotations.SerializedName;
import com.soleap.cashbook.common.document.Document;

public class BsDocument extends Document {

    @SerializedName("name")
    private String name;

    @SerializedName("enable")
    private boolean enable = false;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
