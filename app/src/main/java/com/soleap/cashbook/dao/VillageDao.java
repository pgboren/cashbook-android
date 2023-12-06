package com.soleap.cashbook.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.soleap.cashbook.document.Village;

import java.util.List;

@Dao
public interface VillageDao {
    @Query("SELECT * FROM village")
    List<Village> getAll();

    @Query("SELECT * FROM village WHERE _id IN (:villageIds)")
    List<Village> loadAllByIds(String[] villageIds);

    @Query("SELECT * FROM village WHERE name LIKE :name LIMIT 1")
    Village findByName(String name);

    @Insert
    void insertAll(Village... villages);

    @Delete
    void delete(Village village);

    @Query("DELETE FROM village")
    void deleteAll();

}
