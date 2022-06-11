package com.example.lafo_cheuse.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lafo_cheuse.models.OptionField

@Dao
interface OptionFieldDao {
    @Query("SELECT * FROM OptionField WHERE option_description = :optionDescription")
    fun getOptionFields(optionDescription : String) : LiveData<List<OptionField>>

    @Insert
    fun insertOptionField(field : OptionField) : Long

    @Update
    fun updateOptionField(field : OptionField) : Int
}