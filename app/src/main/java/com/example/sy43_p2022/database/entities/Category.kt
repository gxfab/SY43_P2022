package com.example.sy43_p2022.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category", foreignKeys = [androidx.room.ForeignKey(
    entity = com.example.sy43_p2022.database.entities.Balance::class,
    parentColumns = ["id"],
    childColumns = ["balanceID"],
    onDelete = androidx.room.ForeignKey.CASCADE)])

class Category {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int = 0;

    @ColumnInfo(name = "name")
    var name : String? = null

    @ColumnInfo(name = "totalObjective")
    var totalObjective : Int = 0

    @ColumnInfo(name = "totalSpendings")
    var totalSpendings : Int = 0
    constructor() {}

    constructor(id: Int, name: String, totalObjective: Int, totalSpendings: Int) {
        this.id = id
        this.name = name
        this.totalObjective = totalObjective
        this.totalSpendings = totalSpendings
    }

}