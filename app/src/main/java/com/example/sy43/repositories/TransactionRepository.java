package com.example.sy43.repositories;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.sy43.MainActivity;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.db.mainDB.DB;
import com.example.sy43.db.mainDB.DBexec;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private static TransactionRepository instance;
    private DB db;
    private DBexec databaseExecutor;

    public static TransactionRepository getInstance() {
        if (instance == null) instance = new TransactionRepository();
        return instance;
    }

    TransactionRepository() {
        db = DB.getAppDatabase(MainActivity.getAppContext());
        databaseExecutor = DBexec.getExecutor();
    }




    public MutableLiveData<List<Transaction>> getTransactionsFromCat(int id) {
        MutableLiveData<List<Transaction>> data = new MutableLiveData<>();

        Futures.addCallback(
                db.TransactionDAO().findByCategory(id),
                new FutureCallback<List<Transaction>>() {
                    public void onSuccess(List<Transaction> result) {
                        data.postValue(result);
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                    }
                },
                databaseExecutor
        );
        return data;
    }

    public MutableLiveData<List<Transaction>> getTransactions() {
        MutableLiveData<List<Transaction>> data = new MutableLiveData<>();

        Futures.addCallback(
                db.TransactionDAO().findTrans(),
                new FutureCallback<List<Transaction>>() {
                    public void onSuccess(List<Transaction> result) {
                        data.postValue(result);
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                    }
                },
                databaseExecutor
        );
        return data;
    }

    public void createTransaction(Transaction trans) {
        Futures.addCallback(
                db.TransactionDAO().insert(trans),
                new FutureCallback<Void>() {
                    @Override
                    public void onSuccess(Void stp) {
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                    }
                },
                databaseExecutor
        );
    }
}
