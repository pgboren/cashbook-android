package com.soleap.cashbook.common.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.soleap.cashbook.common.document.RefDocument;
import com.soleap.cashbook.document.Category;

import java.util.List;

@Dao
public interface RefDocumentDao {

    @Query("SELECT * FROM refdocument")
    List<RefDocument> getAll();

    @Query("SELECT * FROM refdocument WHERE _id IN (:ids)")
    List<RefDocument> loadAllByIds(String[] ids);

    @Query("SELECT * FROM refdocument WHERE type = :type")
    List<RefDocument> loadAllByType(String type);

    @Query("SELECT * FROM refdocument WHERE name LIKE :name LIMIT 1")
    RefDocument findByName(String name);

    @Insert
    void insertAll(RefDocument... refdocuments);

    @Delete
    void delete(RefDocument refdocument);

    @Query("DELETE FROM refdocument WHERE type = :type")
    void deleteByType(String type);

    @Query("DELETE FROM refdocument")
    void deleteAll();

}
