package com.example.sy43.viewmodels;


import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sy43.CategoryActivity;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.models.Category;
import com.example.sy43.repositories.CategoryRepository;
import com.example.sy43.repositories.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionViewModel extends ViewModel {
    public TransactionRepository transactionRepo;
    private MutableLiveData<List<Transaction>> trans;

    public void init() {
        transactionRepo = TransactionRepository.getInstance();
        trans = transactionRepo.getTransactions();
    }

    public LiveData<List<Transaction>> getTransactionsFromCat(int catId){
        return transactionRepo.getTransactionsFromCat(catId);
    }
    public LiveData<List<Transaction>> getTransactionsFromSubCat(int subCatId){
        return transactionRepo.getTransactionsFromSubCat(subCatId);
    }
    public LiveData<List<Transaction>> getTransactions(){
        return trans;
    }

    public void createTransaction(final Transaction transaction) {
        this.transactionRepo.createTransaction(transaction);
    }
}
