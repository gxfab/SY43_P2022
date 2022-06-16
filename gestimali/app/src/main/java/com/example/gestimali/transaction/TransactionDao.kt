package com.example.gestimali.transaction

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTransaction(transaction : Transaction)

    @Query("SELECT * FROM T_transaction ORDER BY tra_id,env_id ASC")
    fun readAllData() : LiveData<List<Transaction>>
}