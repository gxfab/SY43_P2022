package com.example.lafo_cheuse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Budget {

    @PrimaryKey(autoGenerate = true)
    var budgetId: Long = 0

    var budgetCategory: String = ""
    var moneyChange: MoneyChange = MoneyChange()
    var duration: Duration = Duration.MONTH
}