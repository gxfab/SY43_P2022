package com.example.sy43_p2022.database.entities

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
data class Category(
    @PrimaryKey (autoGenerate = true)
    val catid: Int = 0,
    val name: String,
    val amount : Int = 0
)
