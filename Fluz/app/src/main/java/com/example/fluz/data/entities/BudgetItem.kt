package com.example.fluz.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BudgetItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Int,
    val budgetId: Int,
    val categoryId: Int
)
