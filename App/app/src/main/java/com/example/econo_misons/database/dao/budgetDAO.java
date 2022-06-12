package com.example.econo_misons.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.Budget_User;
import com.example.econo_misons.database.models.User;

import java.util.List;

@Dao
public interface budgetDAO {
    @Insert
    long addBudget(Budget budget);
    @Update
    void updateBudget(User user);
    @Delete
    void deleteBudget(User user);
    @Query("SELECT * FROM Budget")
    LiveData<List<User>> getAllUsers();
    @Insert
    void linkBudgetUser(Budget_User budUse);
}
