package net.yolopix.moneyz.model.entities

import androidx.room.*

@Dao
interface MonthDao {
    @Query("SELECT * FROM Month")
    suspend fun getAll(): List<Month>

    @Insert
    suspend fun insertMonth(month: Month)
}