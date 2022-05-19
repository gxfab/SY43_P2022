package com.example.lafo_cheuse.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ExpensesBudget(_budget : Budget, _expense : Expense) {
    @PrimaryKey
    @Embedded(prefix = "budget_")
    val budget : Budget = _budget

    @PrimaryKey
    @Embedded(prefix = "expense_")
    val expense : Expense = _expense
}