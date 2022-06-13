package com.example.econo_misons.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Envelope", primaryKeys ={"PREV_DATE", "BUD_ID", "CAT_ID"}, foreignKeys = {
        @ForeignKey(entity = Category.class,
                parentColumns = "ID",
                childColumns = "CAT_ID",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = PrevisionalBudget.class,
                parentColumns = {"YEAR_MONTH", "BUD_ID"},
                childColumns = {"PREV_DATE", "BUD_ID"},
                onDelete = ForeignKey.CASCADE),
})
public class Envelope {

    @NonNull
    @ColumnInfo(name = "PREV_DATE")
    public String dateEnv;

    @NonNull
    @ColumnInfo(name = "BUD_ID")
    public int budgetID;

    @NonNull
    @ColumnInfo(name = "CAT_ID")
    public int categoryID;

    @ColumnInfo(name = "ENV_SUM")
    public float sumEnv;

    public Envelope(int budgetID, String dateEnv, int categoryID, float sumEnv){
        this.budgetID = budgetID;
        this.dateEnv = dateEnv;
        this.categoryID = categoryID;
        this.sumEnv = sumEnv;
    }

    @Ignore
    public Envelope(PrevisionalBudget prevBud, int categoryID, float sumEnv){
        this.budgetID = prevBud.budgetID;
        this.dateEnv = prevBud.yearMonth;
        this.categoryID = categoryID;
        this.sumEnv = sumEnv;
    }

    @Ignore
    public Envelope(PrevisionalBudget prevBud, int categoryID){
        this.budgetID = prevBud.budgetID;
        this.dateEnv = prevBud.yearMonth;
        this.categoryID = categoryID;
        this.sumEnv = 0;
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "dateEnv='" + dateEnv + '\'' +
                ", budgetID=" + budgetID +
                ", categoryID=" + categoryID +
                ", sumEnv=" + sumEnv +
                '}';
    }
}