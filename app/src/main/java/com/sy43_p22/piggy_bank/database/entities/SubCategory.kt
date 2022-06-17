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
/**
 * @description Sub-category Class to match up with the Database
 */
data class SubCategory(
    @PrimaryKey(autoGenerate = true)
    /** Sub-category Id */
    val subid: Int = 0,
    /** Sub-category Name */
    val name: String,
    /** Sub-category spending value */
    val spending: Int = 0,
    /** Sub-category saving value */
    val saving: Int = 0,
    @ColumnInfo(index = true)
    /** Parent Category Id */
    val categoryId: Int
)
