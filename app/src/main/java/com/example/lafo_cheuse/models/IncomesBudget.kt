package com.example.lafo_cheuse.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class IncomesBudget(_budget : Budget, _income : Income) {
    @PrimaryKey
    @Embedded(prefix = "budget_")
    val budget : Budget = _budget

    @PrimaryKey
    @Embedded(prefix = "expense_")
    val income : Income = _income
}