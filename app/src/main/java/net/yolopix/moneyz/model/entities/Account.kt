package net.yolopix.moneyz.model.entities

import androidx.room.*

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val name: String?
)
