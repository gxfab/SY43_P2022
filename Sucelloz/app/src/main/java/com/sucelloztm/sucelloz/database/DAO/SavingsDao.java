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
 * Interface for the savings entity of the dao
 */
@Dao
public interface SavingsDao {
    /**
     * Inserts a saving
     *
     * @param savings saving to insert
     * @return id of the inserted saving
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSaving(Savings savings);

    /**
     * Inserts a list of savings
     *
     * @param savings list fo savings to insert
     * @return ids of the inserted savings
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertSavings(Savings... savings);

    /**
     * Updates a saving
     *
     * @param saving saving to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSaving(Savings saving);

    /**
     * Updates a list of savings
     *
     * @param savings list of savings to update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateSavings(Savings... savings);

    /**
     * Deletes a saving
     *
     * @param saving saving to delete
     */
    @Delete
    void deleteSaving(Savings saving);

    /**
     * Deletes a list of savings
     *
     * @param savings list of savings to delete
     */
    @Delete
    void deleteSavings(Savings... savings);

    /**
     * Query to get all savings
     *
     * @return livedata of all savings
     */
    @Query("SELECT * FROM savings")
    LiveData<List<Savings>> getAllSavings();

    /**
     * Query to get all savings in a list
     *
     * @return list of all savings
     */
    @Query("SELECT * FROM savings")
    List<Savings> getSavings();

    /**
     * Query to get a saving with a specific id
     *
     * @param idOfSaving id searched
     * @return the searched saving
     */
    @Query("SELECT * FROM savings WHERE savings_id=:idOfSaving")
    Savings getSavingById(long idOfSaving);

    /**
     * Query to get the Sum of all savings
     *
     * @return livedata of resulting sum
     */
    @Query("SELECT CAST(total(reached_amount) AS INTEGER) FROM savings")
    LiveData<Integer> getSumOfSavings();
}
