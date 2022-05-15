package com.example.lafo_cheuse.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Category() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categoryId")
    var categoryId: Long = 0

    @ColumnInfo(name = "name")
    var categoryName: String = ""

    @ColumnInfo(name = "emoji")
    var categoryEmoji: String = "💰"

    constructor(categoryName: String,categoryEmoji: String) : this() {
        this.categoryName = categoryName
        this.categoryEmoji = categoryEmoji
    }
}