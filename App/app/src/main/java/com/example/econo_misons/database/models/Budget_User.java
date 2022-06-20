package com.example.econo_misons.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

//Model of the Budget_User Table
@Entity(tableName = "Budget_User", primaryKeys = {"BUD_ID", "USER_ID"}, foreignKeys = {@ForeignKey(entity = Budget.class,
        parentColumns = "ID",
        childColumns = "BUD_ID",
        onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = User.class,
        parentColumns = "ID",
        childColumns = "USER_ID",
        onDelete = ForeignKey.CASCADE)
})
public class Budget_User {
    @NonNull
    @ColumnInfo(name = "BUD_ID")
    public int budgetID;

    @NonNull
    @ColumnInfo(name = "USER_ID")
    public int userID;

    public Budget_User(int budgetID, int userID){
        this.budgetID = budgetID;
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Budget_User{" +
                "budgetID=" + budgetID +
                ", userID=" + userID +
                '}';
    }
}