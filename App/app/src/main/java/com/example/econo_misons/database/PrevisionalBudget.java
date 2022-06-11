package com.example.econo_misons.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PrevisionalBudget")
public class PrevisionalBudget {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "YEAR_MONTH")
    public String yearMonth;

    @ColumnInfo(name = "NAME_BUD")
    public String budgetName;
}