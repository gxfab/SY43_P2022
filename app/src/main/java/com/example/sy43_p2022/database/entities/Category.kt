package com.example.sy43_p2022.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")

class Category {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int = 0

    @ColumnInfo(name = "name")
    var name : String? = null

    @ColumnInfo(name = "totalObjective")
    var totalObjective : Int = 0

    @ColumnInfo(name = "totalSpending")
    var totalSpending : Int = 0

    constructor(id: Int, name: String, totalObjective: Int, totalSpending: Int) {
        this.id = id
        this.name = name
        this.totalObjective = totalObjective
        this.totalSpending = totalSpending
    }

}