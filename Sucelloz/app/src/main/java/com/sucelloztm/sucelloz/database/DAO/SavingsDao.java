package com.sucelloztm.sucelloz.database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.Savings;

import java.util.List;

@Dao
public interface SavingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSaving(Savings savings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertSavings(Savings... savings);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSaving(Savings saving);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSavings(Savings... savings);

    @Delete
    void deleteSaving(Savings saving);

    @Delete
    void deleteSavings(Savings... savings);

    @Query("SELECT * FROM savings")
    LiveData<List<Savings>> getAllSavings();
}
