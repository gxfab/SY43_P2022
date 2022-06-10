package com.example.sy43_p2022.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
class Category(@ColumnInfo(name = "name") var name : String) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    @ColumnInfo(name = "amount") var amount: Int = 0
}
