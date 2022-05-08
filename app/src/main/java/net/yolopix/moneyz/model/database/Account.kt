package net.yolopix.moneyz.model.database

import androidx.room.*

@Entity
data class Account(
    @PrimaryKey val uid: Int,
    val name: String?
)
