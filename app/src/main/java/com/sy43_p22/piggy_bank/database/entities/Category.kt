package com.sy43_p22.piggy_bank.database.entities

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
data class Category(
    @PrimaryKey (autoGenerate = true)
    val catid: Int = 0,
    val name: String,
    val saving : Int = 0,
    val spending : Int = 0,
)
