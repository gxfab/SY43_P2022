package com.example.sy43_projet.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MonthlyRevenue {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "value")
    public double value;

    @ColumnInfo(name = "month")
    public int month;

    public MonthlyRevenue(int id, double value, int month) {
        this.id = id;
        this.value = value;
        this.month = month;
    }
}