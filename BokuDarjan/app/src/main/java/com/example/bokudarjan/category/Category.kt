package com.example.bokudarjan.category
import android.graphics.ColorFilter
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
class Category{
    @PrimaryKey()
    var categoryName : String = ""
    var color : String = ""

    constructor()
    constructor(categoryName: String, color: String){
        this.categoryName = categoryName
        this.color = color
    }



}