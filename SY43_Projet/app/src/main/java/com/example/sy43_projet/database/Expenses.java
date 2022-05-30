package com.example.sy43_projet.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = { @ForeignKey(entity = SubCategory.class, parentColumns = "id",childColumns = "e_subcategory"),
        @ForeignKey(entity = MonthlyRevenue.class, parentColumns = "id",childColumns = "monthlyrevenue")})
public class Expenses {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "e_name")
    public String name;

    @ColumnInfo(name = "e_value")
    public double value;

    @ColumnInfo(name = "e_subcategory")
    public int subcategory;

    @ColumnInfo(name = "e_monthlyrevenue")
    public int monthlyrevenue;

    public Expenses(String name, double value, int subcategory, int monthlyrevenue) {
        this.name = name;
        this.value = value;
        this.subcategory = subcategory;
        this.monthlyrevenue = monthlyrevenue;
    }
}
