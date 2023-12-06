package com.soleap.cashbook.document;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class Commune extends Gazetteer {

    @ColumnInfo(name = "district")
    public String district;

}
