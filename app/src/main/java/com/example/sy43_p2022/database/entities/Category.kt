package com.example.sy43_p2022.database.entities

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    val catid:String,
    val name :String,
    val amount : Int = 0
    )
