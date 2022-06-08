package net.yolopix.moneyz.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This entity represents an account. Each account contains their own categories and expenses
 * @param name The name of the account
 */
@Entity
data class Account(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val name: String?
)
