package com.soleap.cashbook.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.soleap.cashbook.common.dao.RefDocumentDao;
import com.soleap.cashbook.common.document.RefDocument;
import com.soleap.cashbook.dao.CategoryDao;
import com.soleap.cashbook.dao.CommuneDao;
import com.soleap.cashbook.dao.DistrictDao;
import com.soleap.cashbook.dao.ProvinceDao;
import com.soleap.cashbook.dao.VillageDao;
import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.document.Commune;
import com.soleap.cashbook.document.District;
import com.soleap.cashbook.document.Province;
import com.soleap.cashbook.document.Village;

@Database(entities = {RefDocument.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RefDocumentDao refDocumentDao();
//    public abstract DistrictDao districtDao();
//    public abstract CommuneDao communeDao();
//    public abstract VillageDao villageDao();
//    public abstract CategoryDao categoryDao();
}