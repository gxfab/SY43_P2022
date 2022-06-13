package com.example.econo_misons.database.repositories;

import androidx.lifecycle.LiveData;

import com.example.econo_misons.database.dao.transactionDAO;
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.Transaction;

import java.util.List;

public class TransactionDataRepository {

    private final transactionDAO transdao;

    public TransactionDataRepository(transactionDAO transdao) {this.transdao = transdao;}


    public long addTransaction(Transaction trans) {return this.transdao.addTransaction(trans);}

    public void updateTransaction(Transaction trans) {  this.transdao.updateTransaction(trans);}

    public void deleteTransaction(Transaction trans) {  this.transdao.deleteTransaction(trans);}

    public LiveData<List<Transaction>> getAllTransactions() {return this.transdao.getAllTransactions();}

    public LiveData<List<Transaction>> getBudgetTransactions(int budID) {return this.transdao.getBudgetTransactions(budID);}

    public LiveData<List<Transaction>> getBudgetPrevTransactions(PrevisionalBudget prevBud) {return this.transdao.getBudgetPrevTransactions(prevBud.budgetID, prevBud.yearMonth);}

    public LiveData<List<Transaction>> getUserTransactions(int userID) {return this.transdao.getUserTransactions(userID);}

    public LiveData<List<Envelope>> getCurrentBudgetEnvelope(PrevisionalBudget prevBud) {return this.transdao.getCurrentBudgetEnvelope(prevBud.budgetID, prevBud.yearMonth);}

}
