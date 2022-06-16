package com.example.sy43.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Projects {
    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "value")
    public double value;

    @ColumnInfo(name = "percentage")
    public double percentage;

    public Projects(String name, double value, double percentage) {
        this.name = name;
        this.value = value;
        this.percentage = percentage;
    }
}