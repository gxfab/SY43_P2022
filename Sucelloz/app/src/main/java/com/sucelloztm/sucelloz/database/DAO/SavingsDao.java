package com.sucelloztm.sucelloz.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.sucelloztm.sucelloz.models.Savings;

@Dao
public interface SavingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSaving(Savings savings);

    @Insert
    void insertSavings(Savings... savings);

    @Update
    void updateSaving(Savings saving);

    @Update
    void updateSavings(Savings... savings);

    @Delete
    void deleteSaving(Savings saving);

    @Delete
    void deleteSavings(Savings... savings);
}
