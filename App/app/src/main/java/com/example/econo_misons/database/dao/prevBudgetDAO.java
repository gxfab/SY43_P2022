package com.example.econo_misons.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.Budget_User;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.Transaction;

import java.util.List;

@Dao
public interface prevBudgetDAO {
    @Insert
    void addPrevBudget(PrevisionalBudget prevBud);
    @Delete
    void deletePrevBudget(PrevisionalBudget prevBud);
    @Query("SELECT * FROM PrevisionalBudget")
    LiveData<List<PrevisionalBudget>> getAllPrevBudgets();
    @Query("SELECT PrevisionalBudget.BUD_ID,PrevisionalBudget.YEAR_MONTH FROM PrevisionalBudget " +
            "INNER JOIN Budget_User " +
            "ON PrevisionalBudget.BUD_ID = Budget_User.BUD_ID " +
            "WHERE USER_ID = :userID")
    LiveData<List<PrevisionalBudget>> getUserPrevBudgets(int userID);
    @Query("SELECT * FROM PrevisionalBudget WHERE BUD_ID = :budgetID")
    LiveData<List<PrevisionalBudget>> getPrevBudgetsByBudget(int budgetID);

}