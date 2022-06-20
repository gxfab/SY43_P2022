package com.example.econo_misons.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.database.models.TreemapEnv;

import java.util.List;

//Data access object with all the SQL Query for the transactions functions
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
            "WHERE BUD_ID = :budgetID AND PREV_DATE = :prevDate " +
            "GROUP BY CAT_ID")
    LiveData<List<Envelope>> getCurrentBudgetEnvelope(int budgetID, String prevDate);

    @Query("Select " +
            "BUD_ID AS budgetID," +
            "PREV_DATE AS dateEnv," +
            "CAT_ID AS categoryID," +
            "NAME_CAT AS categoryName," +
            "COLOR_CAT AS categoryColor," +
            "SUM(AM_TRANS) AS sumEnv " +
            "FROM `Transaction`" +
            "INNER JOIN Category ON `Transaction`.CAT_ID = Category.ID " +
            "WHERE BUD_ID = :budgetID AND PREV_DATE = :prevDate " +
            "GROUP BY CAT_ID")
    LiveData<List<TreemapEnv>> getTreemapEnv(int budgetID, String prevDate);
}


