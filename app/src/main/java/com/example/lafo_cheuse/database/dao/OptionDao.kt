package com.example.lafo_cheuse.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lafo_cheuse.models.Option

@Dao
interface OptionDao {
    @Query("SELECT * FROM Option")
    fun getOptions() : LiveData<List<Option>>

    @Query("SELECT * FROM Option WHERE description = :optionName")
    fun getOption(optionName : String) : LiveData<Option>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOption(option: Option) : Long
}