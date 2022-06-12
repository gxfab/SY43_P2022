package com.example.econo_misons.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Budget")
public class Budget {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    public int id;

    @ColumnInfo(name = "NAME_BUD")
    public String budgetName;
}