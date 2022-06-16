package com.example.econo_misons.database;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anychart.charts.TreeMap;
import com.anychart.data.Tree;
import com.example.econo_misons.MainActivity;
import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.database.models.TreemapEnv;
import com.example.econo_misons.database.models.User;
import com.example.econo_misons.database.repositories.BudgetDataRepository;
import com.example.econo_misons.database.repositories.CategoryDataRepository;
import com.example.econo_misons.database.repositories.PrevBudgetDataRepository;
import com.example.econo_misons.database.repositories.TransactionDataRepository;
import com.example.econo_misons.database.repositories.UserDataRepository;

import java.security.acl.Owner;
import java.util.List;
import java.util.concurrent.Executor;

public class DBViewModel extends ViewModel {

    /*TODO: Rework a bit the database:
            -Add a recurrent column to transaction ?
    */

    private final UserDataRepository userDataSource;
    private final BudgetDataRepository budgetDataSource;
    private final CategoryDataRepository categoryDataSource;
    private final PrevBudgetDataRepository prevBudgetDataSource;
    private final TransactionDataRepository transactionDataRepository;

    private final Executor executor;


    public  DBViewModel(UserDataRepository userDataSource, BudgetDataRepository budgetDataSource, CategoryDataRepository categoryDataSource, PrevBudgetDataRepository prevBudgetDataSource, TransactionDataRepository transactionDataRepository, Executor executor){
        this.userDataSource = userDataSource;
        this.budgetDataSource = budgetDataSource;
        this.categoryDataSource = categoryDataSource;
        this.prevBudgetDataSource = prevBudgetDataSource;
        this.transactionDataRepository = transactionDataRepository;
        this.executor = executor;
    }



    //USER

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

    public LiveData<List<User>> getUser(int id){ return userDataSource.getUser(id);}


    //BUDGET

    public void addBudget(Budget budget, User user){ executor.execute(() -> budgetDataSource.addBudget(budget, user));}

    /*public void addBudget(Budget budget){
        this.currentUser.observe();
        executor.execute(() -> budgetDataSource.addBudget(budget, this.currentUser));}*/

    public void deleteBudget(Budget budget) {executor.execute(() -> budgetDataSource.deleteBudget(budget));}

    public void updateBudget(Budget budget) {executor.execute(() -> budgetDataSource.updateBudget(budget));}

    public LiveData<List<Budget>> getAllBudgets(){ return budgetDataSource.getAllBudgets();}

    public LiveData<List<Budget>> getUserBudgets(int userID){ return budgetDataSource.getUserBudgets(userID);}

    public LiveData<List<Budget>> getBudgetByID(int budID){ return budgetDataSource.getBudgetByID(budID);}

    public LiveData<Float> getBudgetSum(int budID){ return budgetDataSource.getBudgetSum(budID);}

    public void addUserToBudget(Budget budget, User user){
        executor.execute(() -> budgetDataSource.addUserToBudget(budget, user));
    }



    //CATEGORY

    public void addCategory(Category cat){executor.execute(() -> categoryDataSource.addCategory(cat));}

    public void updateCategory(Category cat){executor.execute(() -> categoryDataSource.updateCategory(cat));}

    public void deleteCategory(Category cat){executor.execute(() -> categoryDataSource.deleteCategory(cat));}

    public LiveData<List<Category>> getAllCategories(){return categoryDataSource.getAllCategories();}

    public LiveData<List<Category>> getCategoryByID(int id){return categoryDataSource.getCategoryByID(id);}

    //PREVISIONAL BUDGET

    public void addPrevBudget(PrevisionalBudget prevBud) {executor.execute(() -> prevBudgetDataSource.addPrevBudget(prevBud));}

    public void deletePrevBudget(PrevisionalBudget prevBud) {executor.execute(() -> prevBudgetDataSource.deletePrevBudget(prevBud));}

    public LiveData<List<PrevisionalBudget>> getAllPrevBudgets() {return prevBudgetDataSource.getAllPrevBudgets();}

    public LiveData<List<PrevisionalBudget>> getPrevBudgetsByBudget(int budgetID) {return prevBudgetDataSource.getPrevBudgetsByBudget(budgetID);}

    public LiveData<List<PrevisionalBudget>> getUserPrevBudgets(int userID) {return prevBudgetDataSource.getUserPrevBudgets(userID);}

    public LiveData<Float> getCurrentBudgetSum(PrevisionalBudget prevBud) {return prevBudgetDataSource.getCurrentBudgetSum(prevBud);}


    //ENVELOPE

    public void addEnvelope(Envelope env) {executor.execute(() -> prevBudgetDataSource.addEnvelope(env));}

    public void updateEnvelope(Envelope env) {executor.execute(() -> prevBudgetDataSource.updateEnvelope(env));}

    public void deleteEnvelope(Envelope env) {executor.execute(() -> prevBudgetDataSource.deleteEnvelope(env));}

    public LiveData<List<Envelope>> getALlEnvelopes() {return prevBudgetDataSource.getALlEnvelopes();}

    public LiveData<List<Envelope>> getPrevBudgetEnvelopes(PrevisionalBudget prevBud) {return prevBudgetDataSource.getPrevBudgetEnvelopes(prevBud);}

    public LiveData<List<Envelope>> getCurrentBudgetEnvelope(PrevisionalBudget prevBud) {return transactionDataRepository.getCurrentBudgetEnvelope(prevBud);}
    //TRANSACTION

    public void addTransaction(Transaction trans) {executor.execute(() -> transactionDataRepository.addTransaction(trans));}

    public void updateTransaction(Transaction trans) {executor.execute(() -> transactionDataRepository.updateTransaction(trans));}

    public void deleteTransaction(Transaction trans) {executor.execute(() -> transactionDataRepository.deleteTransaction(trans));}

    public LiveData<List<Transaction>> getAllTransactions() {return transactionDataRepository.getAllTransactions();}

    public LiveData<List<Transaction>> getBudgetTransactions(int budID) {return transactionDataRepository.getBudgetTransactions(budID);}

    public LiveData<List<Transaction>> getBudgetPrevTransactions(PrevisionalBudget prevBud) {return transactionDataRepository.getBudgetPrevTransactions(prevBud);}

    public LiveData<List<Transaction>> getUserTransactions(int userID) {return transactionDataRepository.getUserTransactions(userID);}

    //TREEMAP
    public LiveData<List<TreemapEnv>> getTreemapList(){
        return transactionDataRepository.getTreeMapList();
    }
    public LiveData<TreeMap> getTreemap(List<TreemapEnv> listEnv){
        return transactionDataRepository.getTreemap(listEnv);
    }

}