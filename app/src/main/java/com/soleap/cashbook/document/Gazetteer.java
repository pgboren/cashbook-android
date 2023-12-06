package com.soleap.cashbook.document;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.soleap.cashbook.common.document.RefDocument;

public class Gazetteer extends RefDocument {

    @ColumnInfo(name = "code")
    public String code;

}
