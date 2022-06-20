package com.example.fluz.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a UserCategory in the database
 */
@Entity(primaryKeys = ["userId", "categoryId"])
data class UserCategory(
    val userId: Int,
    val categoryId: Int,
)
