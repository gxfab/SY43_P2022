package com.example.lafo_cheuse.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class OptionField {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var fieldId: Int = 0

    @ColumnInfo(name = "value")
    var fieldValue: String? = null

    @ColumnInfo(name = "chosen")
    var chosen: Boolean = false
}