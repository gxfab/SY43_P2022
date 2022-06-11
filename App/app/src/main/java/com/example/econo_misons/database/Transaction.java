package com.example.econo_misons.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Transaction", foreignKeys = {@ForeignKey(entity = Budget.class,
        parentColumns = "ID",
        childColumns = "BUD_ID",
        onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = User.class,
                parentColumns = "ID",
                childColumns = "USER_ID",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Category.class,
                parentColumns = "ID",
                childColumns = "CAT_ID",
                onDelete = ForeignKey.CASCADE)
})
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    public int id;

    @ColumnInfo(name = "BUD_ID")
    public int budgetID;

    @ColumnInfo(name = "NAME_TRANS")
    public String transactionName;

    @ColumnInfo(name = "USER_ID")
    public int userID;

    @ColumnInfo(name = "CAT_ID")
    public int categoryID;

    @ColumnInfo(name = "AM_TRANS")
    public float amountTransaction;

    @ColumnInfo(name = "EXPENSE")
    public boolean expense;

    @ColumnInfo(name = "DATE_TRANS")
    public String date;
}