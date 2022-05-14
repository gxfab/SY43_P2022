package com.example.lafo_cheuse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Budget {

    @PrimaryKey(autoGenerate = true)
    var budgetId: Long = 0

    var budgetCategory: String = ""
    var expenses: Double = 0.0
    var incomes: Double = 0.0
    var duration: Duration = Duration.MONTH
}