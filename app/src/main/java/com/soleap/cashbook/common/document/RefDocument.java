package com.soleap.cashbook.common.document;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;
@Entity
public class RefDocument extends Document implements Serializable {

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "type")
    public String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
