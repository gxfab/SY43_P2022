package com.example.sy43_p2022.database.entities

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
    @PrimaryKey
    val subid:String,
    val name :String,
    val amount : Int = 0,
    @ColumnInfo(index = true)
    val categoryId: String


)
