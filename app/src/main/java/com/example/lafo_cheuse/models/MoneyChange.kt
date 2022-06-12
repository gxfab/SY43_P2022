package com.example.lafo_cheuse.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Month

@Entity
open class MoneyChange(
    @ColumnInfo(name = "frequency")
    var frequency: Frequency,

    @ColumnInfo(name = "moneyChangeName")
    var name: String,

    category: Category,

    @ColumnInfo(name= "date_year")
    var dateYear: Int? = null,

    @ColumnInfo(name= "date_month")
    var dateMonth: Int? = null,

    @ColumnInfo(name= "date_day")
    var dateDay: Int? = null
) {

    @PrimaryKey(autoGenerate = true)
    var moneyChangeId: Long = 0

    @Embedded(prefix = "category_")
    var category: Category? = category

}