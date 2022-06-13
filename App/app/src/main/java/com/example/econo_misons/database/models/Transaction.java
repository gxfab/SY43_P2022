package com.example.econo_misons.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Transaction", foreignKeys ={
        @ForeignKey(entity = User.class,
                parentColumns = "ID",
                childColumns = "USER_ID",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Category.class,
                parentColumns = "ID",
                childColumns = "CAT_ID",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = PrevisionalBudget.class,
                parentColumns = {"YEAR_MONTH","BUD_ID"},
                childColumns = {"PREV_DATE","BUD_ID"},
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

<<<<<<< Updated upstream
    @ColumnInfo(name = "PREV_DATE")
    public String prevDate;

    public Transaction(int budgetID, String prevDate, int userID, int categoryID, String transactionName, String date, float amountTransaction, Boolean expense){
=======
    public Transaction(int budgetID, int userID, int categoryID, String transactionName, String date, float amountTransaction, Boolean expense){
>>>>>>> Stashed changes
        this.budgetID = budgetID;
        this.userID = userID;
        this.categoryID = categoryID;
        this.transactionName = transactionName;
        this.date = date;
        this.amountTransaction = amountTransaction;
        this.expense = expense;
        this.prevDate = prevDate;

    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", budgetID=" + budgetID +
                ", transactionName='" + transactionName + '\'' +
                ", userID=" + userID +
                ", categoryID=" + categoryID +
                ", amountTransaction=" + amountTransaction +
                ", expense=" + expense +
                ", date='" + date + '\'' +
                ", prevDate='" + prevDate + '\'' +
                '}';
    }
}