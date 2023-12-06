package com.soleap.cashbook.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.soleap.cashbook.document.District;

import java.util.List;

@Dao
public interface DistrictDao {
    @Query("SELECT * FROM district")
    List<District> getAll();

    @Query("SELECT * FROM district WHERE _id IN (:districtIds)")
    List<District> loadAllByIds(String[] districtIds);

    @Query("SELECT * FROM district WHERE name LIKE :name LIMIT 1")
    District findByName(String name);

    @Insert
    void insertAll(District... districts);

    @Delete
    void delete(District district);

    @Query("DELETE FROM district")
    void deleteAll();

}
