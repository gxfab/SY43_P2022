package net.yolopix.moneyz.model.database

import androidx.room.*

@Dao
interface AccountDao {
    @Query("SELECT * FROM account")
    suspend fun getAll(): List<Account>

}