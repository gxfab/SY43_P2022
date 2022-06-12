package com.example.econo_misons.database.repositories;

import android.util.Log;

import com.example.econo_misons.database.dao.budgetDAO;
import com.example.econo_misons.database.dao.userDAO;
import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.Budget_User;
import com.example.econo_misons.database.models.User;

public class BudgetDataRepository {

    private final budgetDAO budgetDao;

    public BudgetDataRepository(budgetDAO budgetDao) {this.budgetDao = budgetDao;}

    public void addBudget(Budget budget, User user){
        int id = (int)this.budgetDao.addBudget(budget);
        Log.d("DB", "ids : " + id + "," + user.id);
        this.budgetDao.linkBudgetUser(new Budget_User(id, user.id));

    }
}
