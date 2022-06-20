package com.example.econo_misons.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//Model of the Category Table
@Entity(tableName = "Category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    public int id;

    @ColumnInfo(name = "NAME_CAT")
    public String categoryName;

    @ColumnInfo(name = "COLOR_CAT")
    public String color;

    public Category(String categoryName){
        this.categoryName = categoryName;
    }
    @Ignore
    public Category(String categoryName, String color){
        this.categoryName = categoryName;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}