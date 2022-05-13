package com.example.fluz.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Budget(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val start_date: String,
    val end_date: String,
    val total_amount: Int,
    val userId: Int
)
