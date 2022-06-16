package com.example.fluz.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val type: String
) {
    override fun toString(): String {
        return title
    }
}
