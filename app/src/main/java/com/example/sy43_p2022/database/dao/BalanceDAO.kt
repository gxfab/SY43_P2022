package com.example.sy43_p2022.database.dao

import androidx.room.*
import com.example.sy43_p2022.database.entities.Balance
import androidx.lifecycle.LiveData

@Dao
interface BalanceDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategory(balance: Balance)

    @Delete
    suspend fun delete(balance: Balance)

    @Query ("SELECT * FROM balance")
    suspend fun getAllBalance(): LiveData<List<Balance>>

    @Query("SELECT SUM(objective) FROM subcategory")
    suspend fun getAllObjectives(id: Int): LiveData<List<Balance>>

    @Query("SELECT SUM(spending) FROM subcategory WHERE categoryID")
    suspend fun getAllSpending(id: Int): LiveData<List<Balance>>

    @Query("DELETE FROM balance")
    suspend fun nukeTable()
}