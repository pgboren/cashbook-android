package com.soleap.cashbook.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.soleap.cashbook.document.Province;

import java.util.List;

@Dao
public interface ProvinceDao {
    @Query("SELECT * FROM province")
    List<Province> getAll();

    @Query("SELECT * FROM province WHERE _id IN (:provinceIds)")
    List<Province> loadAllByIds(String[] provinceIds);

    @Query("SELECT * FROM province WHERE name LIKE :name LIMIT 1")
    Province findByName(String name);

    @Insert
    void insertAll(Province... provinces);

    @Delete
    void delete(Province province);

    @Query("DELETE FROM province")
    void deleteAll();

}
