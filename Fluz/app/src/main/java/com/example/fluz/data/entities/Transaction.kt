package com.example.fluz.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a transaction in the database
 */
@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Int,
    val type: String,
    val tag: String?,
    val date: String?,
    val categoryId: Int,
    val budgetId: Int?,
    val userId: Int?,
)
