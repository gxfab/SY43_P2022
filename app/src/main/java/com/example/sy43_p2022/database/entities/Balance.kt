package com.example.sy43_p2022.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "balance")
class Balance {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id : Int = 0

    @ColumnInfo(name = "objective")
    var objective : Int = 0

    @ColumnInfo(name = "spending")
    var spending : Int = 0

    constructor(id: Int, objective: Int, spending: Int) {
        this.id = id
        this.objective = objective
        this.spending = spending
    }

}