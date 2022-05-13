package com.example.fluz.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Int,
    val type: String,
    val categoryId: Int,
    val BudgetId: Int?,
    val UserId: Int?,
)
