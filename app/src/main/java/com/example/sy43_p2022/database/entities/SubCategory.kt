package com.example.sy43_p2022.database.entities

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey;

@Entity(tableName = "subcategory", foreignKeys = [
    ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = CASCADE
    )
])
class SubCategory(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "categoryId") var categoryId: Int,
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    @ColumnInfo(name = "amount") var amount : Int = 0
}