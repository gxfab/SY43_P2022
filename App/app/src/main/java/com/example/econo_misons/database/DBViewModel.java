package com.example.econo_misons.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anychart.charts.TreeMap;
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

import java.util.List;
import java.util.concurrent.Executor;
//Public class with all the functions related to the DB that can be called from activities
//It allow us to work on a different thread than the Main/UI thread to avoid freezing the UI.
public class DBViewModel extends ViewModel {


    private final UserDataRepository userDataSource;
    private final BudgetDataRepository budgetDataSource;
    private final CategoryDataRepository categoryDataSource;
    private final PrevBudgetDataRepository prevBudgetDataSource;
    private final TransactionDataRepository transactionDataRepository;

    private final Executor executor;

    //Constructor
    public  DBViewModel(UserDataRepository userDataSource, BudgetDataRepository budgetDataSource, CategoryDataRepository categoryDataSource, PrevBudgetDataRepository prevBudgetDataSource, TransactionDataRepository transactionDataRepository, Executor executor){
        this.userDataSource = userDataSource;
        this.budgetDataSource = budgetDataSource;
        this.categoryDataSource = categoryDataSource;
        this.prevBudgetDataSource = prevBudgetDataSource;
        this.transactionDataRepository = transactionDataRepository;
        this.executor = executor;
    }



    //USER

    //return a list of all users in the DB
    public LiveData<List<User>> getAllUsers() {return userDataSource.getAllUsers();}

    //Add a new user to the DB
    public void newUser(User user) {
        executor.execute(() -> userDataSource.newUser(user));
    }

    //Update an user using the ID of the User object
    public void updateUser(User user) {
        executor.execute(() -> userDataSource.updateUser(user));
    }

    //Delete an user using the ID of the User object
    public void deleteUser(User user) {
        executor.execute(() -> userDataSource.deleteUser(user));
    }

    //return the User object corresponding to the ID
    public LiveData<List<User>> getUser(int id){ return userDataSource.getUser(id);}


    //BUDGET

    //Add a new Bugdet to the DB and link it with an user
    public void addBudget(Budget budget, User user){ executor.execute(() -> budgetDataSource.addBudget(budget, user));}

    //Delete a budget
    public void deleteBudget(Budget budget) {executor.execute(() -> budgetDataSource.deleteBudget(budget));}

    //Update a budget
    public void updateBudget(Budget budget) {executor.execute(() -> budgetDataSource.updateBudget(budget));}

    //Return a list of all the budgets in the DB
    public LiveData<List<Budget>> getAllBudgets(){ return budgetDataSource.getAllBudgets();}

    //Return all the budgets of an User
    public LiveData<List<Budget>> getUserBudgets(int userID){ return budgetDataSource.getUserBudgets(userID);}

    //Return the budget corresponding to the ID
    public LiveData<List<Budget>> getBudgetByID(int budID){ return budgetDataSource.getBudgetByID(budID);}

    //Return the sum of a all the transactions of a budget
    public LiveData<Float> getBudgetSum(int budID){ return budgetDataSource.getBudgetSum(budID);}

    //Add an user to a budget
    public void addUserToBudget(Budget budget, User user){
        executor.execute(() -> budgetDataSource.addUserToBudget(budget, user));
    }



    //CATEGORY

    //Add a new category
    public void addCategory(Category cat){executor.execute(() -> categoryDataSource.addCategory(cat));}

    //Update a category
    public void updateCategory(Category cat){executor.execute(() -> categoryDataSource.updateCategory(cat));}

    //Delete a category
    public void deleteCategory(Category cat){executor.execute(() -> categoryDataSource.deleteCategory(cat));}

    //Return a list of all the categories in the DB
    public LiveData<List<Category>> getAllCategories(){return categoryDataSource.getAllCategories();}

    //return the category with the corresponding ID
    public LiveData<List<Category>> getCategoryByID(int id){return categoryDataSource.getCategoryByID(id);}

    //PREVISIONAL BUDGET

    //Add a new previsional Budget
    public void addPrevBudget(PrevisionalBudget prevBud) {executor.execute(() -> prevBudgetDataSource.addPrevinBudget(prevBud));}

    //Delete a previsional budget
    public void deletePrevBudget(PrevisionalBudget prevBud) {executor.execute(() -> prevBudgetDataSource.deletePrevBudget(prevBud));}

    //Return all the previsional budgets
    public LiveData<List<PrevisionalBudget>> getAllPrevBudgets() {return prevBudgetDataSource.getAllPrevBudgets();}

    //return all the previsional budgets of a given budget
    public LiveData<List<PrevisionalBudget>> getPrevBudgetsByBudget(int budgetID) {return prevBudgetDataSource.getPrevBudgetsByBudget(budgetID);}

    //return all the previsional budgets of a given User
    public LiveData<List<PrevisionalBudget>> getUserPrevBudgets(int userID) {return prevBudgetDataSource.getUserPrevBudgets(userID);}

    //return the sum of the transactions associated with the given previsional budget
    public LiveData<Float> getCurrentBudgetSum(PrevisionalBudget prevBud) {return prevBudgetDataSource.getCurrentBudgetSum(prevBud);}


    //ENVELOPE

    //Add a new envelope
    public void addEnvelope(Envelope env) {executor.execute(() -> prevBudgetDataSource.addEnvelope(env));}

    //update the given envelope
    public void updateEnvelope(Envelope env) {executor.execute(() -> prevBudgetDataSource.updateEnvelope(env));}

    //delete the given envelope
    public void deleteEnvelope(Envelope env) {executor.execute(() -> prevBudgetDataSource.deleteEnvelope(env));}

    //return all the envelopes in the DB
    public LiveData<List<Envelope>> getALlEnvelopes() {return prevBudgetDataSource.getALlEnvelopes();}

    //return all the envelopes of a given previsional budget
    public LiveData<List<Envelope>> getPrevBudgetEnvelopes(PrevisionalBudget prevBud) {return prevBudgetDataSource.getPrevBudgetEnvelopes(prevBud);}

    //Return under an envelope format all the transactions of a given previsional budget (All transaction are grouped by category)
    public LiveData<List<Envelope>> getCurrentBudgetEnvelope(PrevisionalBudget prevBud) {return transactionDataRepository.getCurrentBudgetEnvelope(prevBud);}

    //TRANSACTION

    //Add a new transaction
    public void addTransaction(Transaction trans) {executor.execute(() -> transactionDataRepository.addTransaction(trans));}

    //Update a given transaction
    public void updateTransaction(Transaction trans) {executor.execute(() -> transactionDataRepository.updateTransaction(trans));}

    //delete a given transaction
    public void deleteTransaction(Transaction trans) {executor.execute(() -> transactionDataRepository.deleteTransaction(trans));}

    //return all the transactions in the DB
    public LiveData<List<Transaction>> getAllTransactions() {return transactionDataRepository.getAllTransactions();}

    //get all the transaction sof a given budget
    public LiveData<List<Transaction>> getBudgetTransactions(int budID) {return transactionDataRepository.getBudgetTransactions(budID);}

    //get all the transactions of a given previsional budget
    public LiveData<List<Transaction>> getBudgetPrevTransactions(PrevisionalBudget prevBud) {return transactionDataRepository.getBudgetPrevTransactions(prevBud);}

    //Get all the transactions of a given user
    public LiveData<List<Transaction>> getUserTransactions(int userID) {return transactionDataRepository.getUserTransactions(userID);}

    //TREEMAP

    //Return the list used for the treemap
    public LiveData<List<TreemapEnv>> getTreemapList(){
        return transactionDataRepository.getTreeMapList();
    }

    //Setup and start to draw the treemap using the given list
    public LiveData<TreeMap> getTreemap(List<TreemapEnv> listEnv){
        return transactionDataRepository.getTreemap(listEnv);
    }

}