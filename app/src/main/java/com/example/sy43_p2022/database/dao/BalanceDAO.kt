package com.example.sy43_p2022.database.dao

import androidx.room.*
import com.example.sy43_p2022.database.entities.Balance

@Dao
interface BalanceDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubCategory(balance: Balance)

    @Delete
    fun delete(balance: Balance)

    @Query ("SELECT * FROM balance")
    fun getAllBalance(): List<Balance>

    @Query("SELECT * FROM balance WHERE objective LIKE :objective")
    fun getAllBalanceByObjective(objective: Int): List<Balance>

    @Query("SELECT * FROM balance WHERE spendings LIKE :spendings")
    fun getAllBalanceBySpendings(spendings: Int): List<Balance>

    @Query("DELETE FROM balance")
    fun nukeTable()
}