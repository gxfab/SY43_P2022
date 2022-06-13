package com.example.econo_misons.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.Budget_User;

import java.util.List;

@Dao
public interface budgetDAO {
    @Insert
    long addBudget(Budget budget);
    @Update
    void updateBudget(Budget budget);
    @Delete
    void deleteBudget(Budget budget);
    @Query("SELECT * FROM Budget")
    LiveData<List<Budget>> getAllBudgets();
    @Query("SELECT ID,NAME_BUD FROM Budget INNER JOIN Budget_User ON ID = BUD_ID WHERE USER_ID = :userID")
    LiveData<List<Budget>> getUserBudgets(int userID);
    @Query("SELECT * FROM Budget WHERE ID = :budgetID")
    LiveData<List<Budget>> getBudgetByID(int budgetID);
    @Query("SELECT SUM(AM_TRANS) FROM Budget INNER JOIN `Transaction` WHERE Budget.ID = :budgetID")
    LiveData<Float> getBudgetSum(int budgetID);
    @Insert
    void linkBudgetUser(Budget_User budUse);
}
