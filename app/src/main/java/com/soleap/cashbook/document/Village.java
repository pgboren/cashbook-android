package com.soleap.cashbook.document;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class Village extends Gazetteer {
    @ColumnInfo(name = "commune")
    public String commune;

}
