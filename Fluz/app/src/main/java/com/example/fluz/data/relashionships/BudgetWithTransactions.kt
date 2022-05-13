package com.example.fluz.data.relashionships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.Transaction

data class BudgetWithTransactions(
    @Embedded val budget: Budget,
    @Relation(
        parentColumn = "id",
        entityColumn = "budgetId"
    )
    val transactions: List<Transaction>
)
