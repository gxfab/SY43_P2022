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

/**
 * interface for the savings entity of the dao
 */
@Dao
public interface SavingsDao {
    /**
     * inserts a saving in the dao
     * @param savings saving to insert
     * @return id of the inserted saving
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSaving(Savings savings);

    /**
     * inserts a list of savings
     * @param savings list fo savings to insert
     * @return ids of the inserted savings
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertSavings(Savings... savings);

    /**
     * updates a saving
     * @param saving saving to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSaving(Savings saving);

    /**
     * updates a list of savings
     * @param savings list of savings to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSavings(Savings... savings);

    /**
     * deletes a saving
     * @param saving saving to delete
     */
    @Delete
    void deleteSaving(Savings saving);

    /**
     * deletes a list of savings
     * @param savings list of savings to delete
     */
    @Delete
    void deleteSavings(Savings... savings);

    /**
     * query to get all savings in a livedata
     * @return livedata of all savings
     */
    @Query("SELECT * FROM savings")
    LiveData<List<Savings>> getAllSavings();

    /**
     * query to get all savings in a list
     * @return list of all savings
     */
    @Query("SELECT * FROM savings")
    List<Savings> getSavings();

    /**
     * query to get a saving with a specific id
     * @param idOfSaving id searched
     * @return the searched saving
     */
    @Query("SELECT * FROM savings WHERE savings_id=:idOfSaving")
    Savings getSavingById(long idOfSaving);

    @Query("SELECT CAST(total(reached_amount) AS INTEGER) FROM savings")
    LiveData<Integer> getSumOfSavings();
}
