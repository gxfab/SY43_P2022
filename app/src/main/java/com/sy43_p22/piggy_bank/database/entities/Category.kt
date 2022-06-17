package com.sy43_p22.piggy_bank.database.entities

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
/**
 * @description Category Class to match up with the Database
 */
data class Category(
    @PrimaryKey (autoGenerate = true)
    /** Category Id */
    val catid: Int = 0,
    /** Category Name */
    val name: String,
    /** Category saving value */
    val saving : Int = 0,
    /** Category spending value */
    val spending : Int = 0,
)
