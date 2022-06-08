package com.example.sy43.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = SubCategory.class, parentColumns = "id",childColumns = "b_subcategory")})
public class Bills {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "b_name")
    public String name;

    @ColumnInfo(name = "e_value")
    public double value;

    @ColumnInfo(name = "b_subcategory")
    public int category;

    public Bills(String name, double value, int category) {
        this.name = name;
        this.value = value;
        this.category = category;
    }
}
