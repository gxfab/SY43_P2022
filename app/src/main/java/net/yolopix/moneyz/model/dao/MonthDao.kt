package net.yolopix.moneyz.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import net.yolopix.moneyz.model.entities.Month

@Dao
interface MonthDao {
    @Query("SELECT * FROM Month where accountUid == :accountUid ORDER BY yearNumber, monthNumber")
    suspend fun getMonthsForAccountUid(accountUid: Int): List<Month>

    @Insert
    suspend fun insertMonth(month: Month)
}