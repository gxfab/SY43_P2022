package com.example.econo_misons.database;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.User;
import com.example.econo_misons.database.repositories.BudgetDataRepository;
import com.example.econo_misons.database.repositories.UserDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class DBViewModel extends ViewModel {

    /*TODO: Rework a bit the database:
            -Link Budget and previsional budget ?
            -Add a color to category ?
            -Add a prev column to Transaction ?
            -Add a recurrent column to transaction ?
      TODO: Add the methods (on split DAOs and Repositories):
            -addCategory(Budget budget, String name)
            -addBudget(User user, String name)
            -getBudgetList(User user)
            -getCatList(User user, Budget bud, String Prev_Date) return Cat list with name and sum
            -getCatExp(Category cat)
            -addExp(User user, Budget bud, Category cat, String name, float amount, bool exp, String date)
            -editExp(Transaction exp)
            -deleteExp(Transaction exp)
    */

    private final UserDataRepository userDataSource;
    private final BudgetDataRepository budgetDataSource;

    private final Executor executor;

    @Nullable
    private LiveData<User> currentUser;

    public  DBViewModel(UserDataRepository userDataSource, BudgetDataRepository budgetDataSource, Executor executor){
        this.userDataSource = userDataSource;
        this.budgetDataSource = budgetDataSource;
        this.executor = executor;
    }

    public LiveData<List<User>> getAllUsers() {return userDataSource.getAllUsers();}

    public void newUser(User user) {
        executor.execute(() -> userDataSource.newUser(user));
    }

    public void updateUser(User user) {
        executor.execute(() -> userDataSource.updateUser(user));
    }

    public void deleteUser(User user) {
        executor.execute(() -> userDataSource.deleteUser(user));
    }

    public void addBudget(Budget budget, User user){ executor.execute(() -> budgetDataSource.addBudget(budget, user));}

    public LiveData<List<User>> getUser(int id){ return userDataSource.getUser(id);}
}
