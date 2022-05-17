package net.yolopix.moneyz.model.database

import androidx.room.*

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val name: String?
)
