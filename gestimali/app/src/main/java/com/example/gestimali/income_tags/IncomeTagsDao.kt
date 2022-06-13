package com.example.gestimali.income_tags

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IncomeTagsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIncomeTag(incTags : IncomeTags)

    @Query("SELECT * FROM T_income_tags ORDER BY inc_id ASC")
    fun readAllData() : LiveData<List<IncomeTags>>
}