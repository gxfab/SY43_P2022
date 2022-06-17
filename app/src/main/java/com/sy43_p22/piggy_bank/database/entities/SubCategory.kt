package com.sy43_p22.piggy_bank.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "subcategory", foreignKeys = [
    ForeignKey(
        entity = Category::class,
        parentColumns = ["catid"],
        childColumns = ["categoryId"],
        onDelete = CASCADE
    )
])
data class SubCategory(
    @PrimaryKey(autoGenerate = true)
    val subid: Int = 0,
    val name: String,
    val spending: Int = 0,
    val saving: Int = 0,
    @ColumnInfo(index = true)
    val categoryId: Int
)
