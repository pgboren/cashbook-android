package com.soleap.cashbook.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.document.Village;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Query("SELECT * FROM category WHERE _id IN (:categoryIds)")
    List<Category> loadAllByIds(String[] categoryIds);

    @Query("SELECT * FROM category WHERE name LIKE :name LIMIT 1")
    Category findByName(String name);

    @Insert
    void insertAll(Category... categories);

    @Delete
    void delete(Category category);

    @Query("DELETE FROM category")
    void deleteAll();

}
