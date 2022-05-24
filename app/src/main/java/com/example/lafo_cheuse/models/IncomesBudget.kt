package com.example.lafo_cheuse.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["budget_budgetId","expense_moneyChangeId"])
class IncomesBudget(
    @Embedded(prefix = "budget_")
    var budget: Budget,

    @Embedded(prefix = "expense_")
    var income: Income
) {}