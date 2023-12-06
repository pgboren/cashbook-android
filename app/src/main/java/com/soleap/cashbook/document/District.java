package com.soleap.cashbook.document;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class District extends Gazetteer {
    @ColumnInfo(name = "province")
    public String province;
}
