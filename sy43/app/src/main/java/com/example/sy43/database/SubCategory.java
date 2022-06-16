package com.example.sy43.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Category.class, parentColumns = "id",childColumns = "category")})
public class SubCategory {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "s_name")
    public String name;

    @ColumnInfo(name = "envelope")
    public double envelope;

    @ColumnInfo(name = "category")
    public int category;

    public String getName() {
        return name;
    }

    public SubCategory(String name, double envelope, int category) {
        this.name = name;
        this.envelope = envelope;
        this.category = category;
    }
}