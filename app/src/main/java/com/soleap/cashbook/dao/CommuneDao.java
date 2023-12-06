package com.soleap.cashbook.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.soleap.cashbook.document.Commune;

import java.util.List;

@Dao
public interface CommuneDao {
    @Query("SELECT * FROM commune")
    List<Commune> getAll();

    @Query("SELECT * FROM commune WHERE _id IN (:communeIds)")
    List<Commune> loadAllByIds(String[] communeIds);

    @Query("SELECT * FROM commune WHERE name LIKE :name LIMIT 1")
    Commune findByName(String name);

    @Insert
    void insertAll(Commune... communes);

    @Delete
    void delete(Commune commune);

    @Query("DELETE FROM commune")
    void deleteAll();

}
