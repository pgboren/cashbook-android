package com.soleap.cashbook.document;

import com.soleap.cashbook.common.document.BsDocument;

import java.util.Map;

public class Color extends BsDocument {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> data = super.toMap();
        data.put("code", getCode());
        return  data;
    }
}