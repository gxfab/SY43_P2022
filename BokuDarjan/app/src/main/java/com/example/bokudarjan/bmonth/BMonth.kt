package com.example.bokudarjan.bmonth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "month")
class BMonth(name: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name : String = name
    var sum: Float = 0f;
}