package com.example.econo_misons.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.database.models.User;

import java.util.List;

@Dao
public interface transactionDAO {
    @Insert
    long addTransaction(Transaction transaction);
    @Update
    void updateTransaction(Transaction transaction);
    @Delete
    void deleteTransaction(Transaction transaction);
    @Query("SELECT * FROM `Transaction`")
    LiveData<List<Transaction>> getAllTransactions();
    @Query("SELECT * FROM `Transaction` WHERE `Transaction`.BUD_ID = :budID")
    LiveData<List<Transaction>> getBudgetTransactions(int budID);
    @Query("SELECT * FROM `Transaction` WHERE " +
            "`Transaction`.BUD_ID = :budID " +
            "AND `Transaction`.PREV_DATE = :prevDate")
    LiveData<List<Transaction>> getBudgetPrevTransactions(int budID, String prevDate);
    @Query("SELECT * FROM `Transaction` WHERE `Transaction`.USER_ID = :userID")
    LiveData<List<Transaction>> getUserTransactions(int userID);
}