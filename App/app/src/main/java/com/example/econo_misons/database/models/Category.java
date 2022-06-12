package com.example.econo_misons.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    public int id;

    @ColumnInfo(name = "NAME_CAT")
    public String categoryName;

    public Category(String Name){
        this.categoryName = Name;
    }
}
