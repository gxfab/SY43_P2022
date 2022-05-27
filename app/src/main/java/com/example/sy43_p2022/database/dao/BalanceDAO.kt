package com.example.sy43_p2022.database.dao

import androidx.room.*
import com.example.sy43_p2022.database.entities.Balance
import com.example.sy43_p2022.database.entities.Category

@Dao
interface BalanceDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategory(balance: Balance)

    @Delete
    suspend fun delete(balance: Balance)

    @Query ("SELECT * FROM balance")
    suspend fun getAllBalance(): List<Balance>

    @Query("SELECT SUM(objective) FROM subcategory")
    suspend fun getAllObjectives(id: Int): List<Balance>

    @Query("SELECT SUM(spendings) FROM subcategory WHERE categoryID")
    suspend fun getAllSpendings(id: Int): List<Balance>

    @Query("DELETE FROM balance")
    suspend fun nukeTable()
}