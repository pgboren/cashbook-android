package com.soleap.cashbook.datasync;

import android.content.Context;
import android.os.AsyncTask;

import com.soleap.cashbook.common.document.RefDocument;
import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.document.Commune;
import com.soleap.cashbook.document.District;
import com.soleap.cashbook.document.Province;
import com.soleap.cashbook.document.Village;
import com.soleap.cashbook.room.AppDatabase;

public class RefDataSyncTask extends AsyncTask<Object, Void, Void> {
    private final RefData data;

    private Context context;

    private AppDatabase db;

    public RefDataSyncTask(Context context, AppDatabase db, RefData data) {
        this.context = context;
        this.db = db;
        this.data = data;
    }

    @Override
    protected Void doInBackground(Object... objects) {
        db.refDocumentDao().deleteAll();
        db.refDocumentDao().insertAll(data.getData().toArray(new RefDocument[0]));
//        db.districtDao().deleteAll();
//        db.districtDao().insertAll(data.getDistricts().toArray(new District[0]));
//        db.communeDao().deleteAll();
//        db.communeDao().insertAll(data.getCommunes().toArray(new Commune[0]));
//        db.villageDao().deleteAll();
//        db.villageDao().insertAll(data.getVillages().toArray(new Village[0]));
//        db.categoryDao().deleteAll();
//        db.categoryDao().insertAll(data.getCategories().toArray(new Category[0]));
        return  null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        // Update UI if needed
    }
}