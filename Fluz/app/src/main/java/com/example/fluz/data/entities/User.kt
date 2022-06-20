package com.example.fluz.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Represents a user in the database
 */
@Entity(indices = [Index(value = ["email_address"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val email_address: String,
    val hash_password: String,
    val currency: String,
    val budget_start_day: Int,
    val created_at: String
)
