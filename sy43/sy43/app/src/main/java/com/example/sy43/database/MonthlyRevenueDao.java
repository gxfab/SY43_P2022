package com.example.sy43.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MonthlyRevenueDao {
    @Query("SELECT * FROM MonthlyRevenue")
    LiveData<List<MonthlyRevenue>> getAll();

    @Query("SELECT * FROM MonthlyRevenue WHERE id IN (:revID)")
    LiveData<List<MonthlyRevenue>> loadAllByIDs(int[] revID);

    @Query("SELECT * FROM MonthlyRevenue WHERE month = :month")
    MonthlyRevenue findByMonth(int month);

    @Insert
    void insertAll(MonthlyRevenue... rev);

    @Delete
    void delete(MonthlyRevenue rev);
}
