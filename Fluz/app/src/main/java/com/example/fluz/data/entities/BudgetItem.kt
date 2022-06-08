package com.example.fluz.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BudgetItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Int,
    var budgetId: Int,
    val categoryId: Int
)
