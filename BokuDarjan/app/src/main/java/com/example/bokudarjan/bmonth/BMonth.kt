package com.example.bokudarjan.bmonth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "month")
/**
 * A database entity class used to represent month. Called BMonth because the class Month already exists in Java
 */
class BMonth(name: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name : String = name
    var saving: Float = 0f;
}