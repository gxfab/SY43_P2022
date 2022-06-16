package com.example.sy43.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MonthlyRevenue {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "value")
    public double value;

    @ColumnInfo(name = "month")
    public int month;

    @ColumnInfo(name = "year")
    public int year;

    public MonthlyRevenue(double value, int month, int year) {
        this.value = value;
        this.month = month;
    }
}
