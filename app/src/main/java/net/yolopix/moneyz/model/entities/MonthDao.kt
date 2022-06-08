package net.yolopix.moneyz.model.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MonthDao {
    @Query("SELECT * FROM Month where accountUid == :accountUid ")
    suspend fun getMonthsForAccountUid(accountUid: Int): List<Month>

    @Insert
    suspend fun insertMonth(month: Month)
}