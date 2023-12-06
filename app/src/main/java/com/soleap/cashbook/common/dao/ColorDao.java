package com.soleap.cashbook.common.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.soleap.cashbook.common.document.Color;
import com.soleap.cashbook.common.document.RefDocument;

import java.util.List;

@Dao
public interface ColorDao {

    @Query("SELECT * FROM color")
    List<Color> getAll();

    @Query("SELECT * FROM color WHERE _id IN (:ids)")
    List<Color> loadAllByIds(String[] ids);

    @Query("SELECT * FROM color WHERE type = :type")
    List<Color> loadAllByType(String type);

    @Query("SELECT * FROM color WHERE name LIKE :name LIMIT 1")
    Color findByName(String name);

    @Insert
    void insertAll(Color... colors);

    @Delete
    void delete(Color color);

    @Query("DELETE FROM color WHERE type = :type")
    void deleteByType(String type);

    @Query("DELETE FROM color")
    void deleteAll();

}
