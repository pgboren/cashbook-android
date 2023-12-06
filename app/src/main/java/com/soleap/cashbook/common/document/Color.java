package com.soleap.cashbook.common.document;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

import kotlin.jvm.internal.Ref;

@Entity
public class Color extends RefDocument {

    @ColumnInfo(name = "code")
    public String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
