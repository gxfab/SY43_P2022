package com.example.sy43_p2022.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey;

@Entity(tableName = "subcategory", foreignKeys = [ForeignKey(entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryID"],
            onDelete = CASCADE)])

class SubCategory {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int = 0

    @ColumnInfo(name = "categoryID")
    var categoryID : Int = 0;

    @ColumnInfo(name = "name")
    var name : String? = null

    @ColumnInfo(name = "objective")
    var objective : Int = 0

    @ColumnInfo(name = "spendings")
    var spendings : Int = 0
    constructor() {}

    constructor(id: Int, name: String, objective: Int, spendings: Int) {
        this.id = id
        this.name = name
        this.objective = objective
        this.spendings = spendings
    }

}