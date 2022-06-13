package com.example.gestimali.fixexpense

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FixedExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFixedExpense(fixedExpense : FixedExpense)

    @Query("SELECT * FROM T_fixed_expense ORDER BY exp_id ASC")
    fun readAllData() : LiveData<List<FixedExpense>>
}