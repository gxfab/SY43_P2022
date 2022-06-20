package com.example.econo_misons.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

//Model of the PrevisionalBudget Table
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

    public PrevisionalBudget(int budgetID, String yearMonth){
        this.budgetID = budgetID;
        this.yearMonth = yearMonth;
    }

    @Override
    public String toString() {
        return "PrevisionalBudget{" +
                "yearMonth='" + yearMonth + '\'' +
                ", budgetID=" + budgetID +
                '}';
    }
}