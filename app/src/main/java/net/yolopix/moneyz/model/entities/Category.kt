package net.yolopix.moneyz.model.entities

import androidx.room.*

@Entity
data class Category(
    @PrimaryKey val uid: Int,
    var color: Int,
    var name: String
)