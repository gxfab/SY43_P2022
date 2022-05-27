package com.example.sy43_p2022.database.entities

import androidx.annotation.NonNull
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
class SubCategory {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int = 0

    @ColumnInfo(name = "categoryId")
    var parentCategoryId : Int = 0

    @ColumnInfo(name = "name")
    var name : String = "Unknown"

    @ColumnInfo(name = "objective")
    var objective : Int = 0

    @ColumnInfo(name = "spending")
    var spending : Int = 0

    constructor(name: String, parentCategoryId: Int) {
        this.name = name
        this.parentCategoryId = parentCategoryId
    }

}