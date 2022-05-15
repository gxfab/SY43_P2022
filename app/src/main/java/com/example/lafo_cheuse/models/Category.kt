package com.example.lafo_cheuse.models

import androidx.appcompat.widget.EmojiCompatConfigurationView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Category {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categoryId")
    var categoryId: Long = 0

    @ColumnInfo(name = "name")
    var categoryName: String = ""

    @ColumnInfo(name = "emoji")
    var categoryEmoji: String = "0x1F4B0"
}