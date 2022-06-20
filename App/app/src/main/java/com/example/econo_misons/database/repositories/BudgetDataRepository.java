package com.example.econo_misons.database.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.econo_misons.database.dao.budgetDAO;
import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.Budget_User;
import com.example.econo_misons.database.models.User;

import java.util.List;

//The functions called by the DBViewModel on a new thread.
//The comments for the functions are in the DBViewModel file if they aren't here
public class BudgetDataRepository {

    private final budgetDAO budgetDao;

    public BudgetDataRepository(budgetDAO budgetDao) {this.budgetDao = budgetDao;}

    //Add a new budget to the DB then link it with the given User
    public void addBudget(Budget budget, User user){
        int id = (int)this.budgetDao.addBudget(budget);
        Log.d("DB", "ids : " + id + "," + user.id);
        this.budgetDao.linkBudgetUser(new Budget_User(id, user.id));
    }

    public void addUserToBudget(Budget budget, User user){
        this.budgetDao.linkBudgetUser(new Budget_User(budget.id, user.id));
    }

    public void updateBudget(Budget budget){
        this.budgetDao.updateBudget(budget);
    }

    public void deleteBudget(Budget budget){
        this.budgetDao.deleteBudget(budget);
    }

    public LiveData<List<Budget>> getAllBudgets(){
        return this.budgetDao.getAllBudgets();
    }

    public LiveData<List<Budget>> getUserBudgets(int userID){
        return this.budgetDao.getUserBudgets(userID);
    }

    public LiveData<List<Budget>> getBudgetByID(int budgetID){
        return this.budgetDao.getBudgetByID(budgetID);
    }

    public LiveData<Float> getBudgetSum(int budgetID){
        return this.budgetDao.getBudgetSum(budgetID);
    }
}
