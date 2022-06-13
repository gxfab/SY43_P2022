package com.example.econo_misons.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.Transaction;

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
    @Query("Select BUD_ID,PREV_DATE,CAT_ID,SUM(AM_TRANS) AS ENV_SUM FROM `Transaction`" +
            "WHERE BUD_ID = :BudgetID AND PREV_DATE = :prevDate " +
            "GROUP BY CAT_ID")
    LiveData<List<Envelope>> getCurrentBudgetEnvelope(int BudgetID, String prevDate);
}