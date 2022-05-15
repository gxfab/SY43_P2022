package com.example.lafo_cheuse.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class MoneyChange(
    @ColumnInfo(name = "frequency") var frequency: Frequency,
    category: Category
) {

    @PrimaryKey(autoGenerate = true)
    var moneyChangeId: Long = 0

    @Embedded(prefix = "category_")
    var category: Category? = category

}