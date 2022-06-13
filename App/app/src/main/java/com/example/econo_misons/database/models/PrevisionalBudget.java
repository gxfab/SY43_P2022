package com.example.econo_misons.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "PrevisionalBudget", primaryKeys = {"BUD_ID", "YEAR_MONTH"}, foreignKeys = {
        @ForeignKey(entity = Budget.class,
                parentColumns = "ID",
                childColumns = "BUD_ID",
                onDelete = ForeignKey.CASCADE)
})
public class PrevisionalBudget {
    @NonNull
    @ColumnInfo(name = "YEAR_MONTH")
    public String yearMonth;

    @NonNull
    @ColumnInfo(name = "BUD_ID")
    public int budgetID;

}