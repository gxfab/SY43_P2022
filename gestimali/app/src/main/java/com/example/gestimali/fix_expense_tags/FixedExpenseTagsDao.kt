package com.example.gestimali.fix_expense_tags

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FixedExpenseTagsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFixedExpenseTag(expTag : FixedExpenseTags)

    @Query("SELECT * FROM T_fixed_expense_tags ORDER BY exp_id ASC")
    fun readAllData() : LiveData<List<FixedExpenseTags>>
}