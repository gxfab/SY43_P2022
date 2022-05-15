package com.example.lafo_cheuse.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Budget {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "budgetId")
    var budgetId: Long = 0

    @ColumnInfo(name = "category")
    var budgetCategory: String = ""
    @ColumnInfo(name = "moneyChange")
    var moneyChange: MoneyChange = MoneyChange()
    @ColumnInfo(name = "duration")
    var duration: Duration = Duration.MONTH
}