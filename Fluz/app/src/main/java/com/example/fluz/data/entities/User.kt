package com.example.fluz.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val username: String,
    val email_address: String,
    val hash_password: String,
    val currency: String,
    val created_at: String
)
