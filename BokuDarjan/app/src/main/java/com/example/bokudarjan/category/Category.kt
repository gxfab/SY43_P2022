package com.example.bokudarjan.category
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
class Category{
    @PrimaryKey()
    var categoryName : String = ""

    constructor()
    constructor(categoryName: String){
        this.categoryName = categoryName
    }



}