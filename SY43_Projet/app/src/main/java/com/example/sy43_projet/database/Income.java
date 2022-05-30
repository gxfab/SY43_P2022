package com.example.sy43_projet.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = MonthlyRevenue.class, parentColumns = "id",childColumns = "i_monthlyrevenue")})
public class Income {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "i_name")
    public String name;

    @ColumnInfo(name = "i_value")
    public double value;

    @ColumnInfo(name = "i_monthlyrevenue")
    public int monthlyrevenue;

    public Income(String name, double value, int monthlyrevenue) {
        this.name = name;
        this.value = value;
        this.monthlyrevenue = monthlyrevenue;
    }
}

