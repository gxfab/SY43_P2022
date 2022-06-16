package com.example.sy43.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = { @ForeignKey(entity = SubCategory.class, parentColumns = "id",childColumns = "e_subcategory"),
        @ForeignKey(entity = MonthlyRevenue.class, parentColumns = "id",childColumns = "e_monthlyrevenue")})
public class Expenses {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "e_name")
    public String name;

    @ColumnInfo(name = "e_value")
    public double value;

    @ColumnInfo(name = "e_date")
    public String date;

    @ColumnInfo(name = "e_subcategory")
    public int subcategory;

    @ColumnInfo(name = "e_monthlyrevenue")
    public int monthlyrevenue;

    public Expenses(String name, double value, String date, int subcategory, int monthlyrevenue) {
        this.name = name;
        this.value = value;
        this.date = date;
        this.subcategory = subcategory;
        this.monthlyrevenue = monthlyrevenue;
    }
}
