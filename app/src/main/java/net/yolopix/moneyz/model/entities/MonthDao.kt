package net.yolopix.moneyz.model.entities

import androidx.room.*

@Dao
interface MonthDao {
    @Query("SELECT * FROM Month")
    suspend fun getAll(): List<Month>

    @Query("SELECT * FROM Month where accountUid == :accountUid ")
    suspend fun getMonthsForAccountUid(accountUid: Int): List<Month>

    @Insert
    suspend fun insertMonth(month: Month)
}