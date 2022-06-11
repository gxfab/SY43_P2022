package com.example.econo_misons.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Envelope", foreignKeys = {@ForeignKey(entity = Category.class,
        parentColumns = "ID",
        childColumns = "CAT_ID",
        onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = PrevisionalBudget.class,
                parentColumns = "YEAR_MONTH",
                childColumns = "PREV_DATE",
                onDelete = ForeignKey.CASCADE)
})
public class Envelope {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "PREV_DATE")
    public String dateEnv;

    @ColumnInfo(name = "CAT_ID")
    public int categoryID;

    @ColumnInfo(name = "ENV_SUM")
    public float sumEnv;
}