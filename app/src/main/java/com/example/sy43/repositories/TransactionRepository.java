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
    /**
     * Renvoi l'instance de TransactionRepository
     *
     * @return l'instance de TransactionRepository
     */
    public static TransactionRepository getInstance() {
        if (instance == null) instance = new TransactionRepository();
        return instance;
    }

    TransactionRepository() {
        db = DB.getAppDatabase(MainActivity.getAppContext());
        databaseExecutor = DBexec.getExecutor();
    }


    /**
     * Retourne toutes les transactions de la sub catégorie avec l'id passée en paramètre
     * @param id id de la sub catégorie
     * @return MutableLiveData<List<Transaction>> Liste des sous transactions trouvées
     */

    public MutableLiveData<List<Transaction>> getTransactionsFromSubCat(int id) {
        MutableLiveData<List<Transaction>> data = new MutableLiveData<>();

        Futures.addCallback(
                db.TransactionDAO().findBySubCategory(id),
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
    /**
     * Retourne toutes les transactions de la catégorie avec l'id passée en paramètre
     *
     * @param id id de la catégorie
     * @return MutableLiveData<List<Transaction>> Liste des sous transactions trouvées
     */
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

    /**
     * Retourne toutes les transactions de la BDD
     *
     * @return  MutableLiveData<List<Transaction>> liste des transactions
     */
    public MutableLiveData<List<Transaction>> getTransactions() {
        MutableLiveData<List<Transaction>> data = new MutableLiveData<>();

        Futures.addCallback(
                db.TransactionDAO().findTrans(),
                new FutureCallback<List<Transaction>>() {
                    public void onSuccess(List<Transaction> result) {
                        data.postValue(result);
                        Log.d("Test17", String.valueOf(result.size()));
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                     }
                },
                databaseExecutor
        );
        return data;
    }
    /**
     * Crée une nouvelle transaction.
     *
     * @param Transaction Transaction à créer
     */
    public void createTransaction(Transaction trans) {
        Futures.addCallback(
                db.TransactionDAO().insert(trans),
                new FutureCallback<Void>() {
                    @Override
                    public void onSuccess(Void stp) {
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                        Log.d("Test10", String.valueOf(thrown));
                    }
                },
                databaseExecutor
        );
    }
}
