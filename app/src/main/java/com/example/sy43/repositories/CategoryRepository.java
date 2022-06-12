package com.example.sy43.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


import com.example.sy43.MainActivity;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.mainDB.DB;
import com.example.sy43.db.mainDB.DBexec;
import com.example.sy43.models.Category;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class CategoryRepository {
    private static CategoryRepository instance;
    private ArrayList<Categorydb> dataSet = new ArrayList<>();
    private DB db;
    private DBexec databaseExecutor;

    public static CategoryRepository getInstance() {
        if (instance == null) instance = new CategoryRepository();
        return instance;
    }

    CategoryRepository() {
        db = DB.getAppDatabase(MainActivity.getAppContext());
        databaseExecutor = DBexec.getExecutor();
    }

    public MutableLiveData<Categorydb> getCategoryById(int id) {
        MutableLiveData<Categorydb> data = new MutableLiveData<>();

        Futures.addCallback(
                db.CategoryDAO().findByID(id),
                new FutureCallback<Categorydb>() {
                    public void onSuccess(Categorydb result) {
                        data.postValue(result);
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                    }
                },
                //MainActivity.getAppContext().getMainExecutor()
                databaseExecutor
        );
        return data;
    }
    public MutableLiveData<List<Categorydb>> getCategories() {
        MutableLiveData<List<Categorydb>> data = new MutableLiveData<>();

        Futures.addCallback(
                db.CategoryDAO().findCategories(),
                new FutureCallback<List<Categorydb>>() {
                    public void onSuccess(List<Categorydb> result) {
                        data.postValue(result);
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                        Log.d("Test", String.valueOf(thrown));
                    }
                },
                //MainActivity.getAppContext().getMainExecutor()
                databaseExecutor
        );
        return data;
    }

    public MutableLiveData<List<Categorydb>> getObjectives() {
        MutableLiveData<List<Categorydb>> data = new MutableLiveData<>();

        Futures.addCallback(
                db.CategoryDAO().findObjectives(),
                new FutureCallback<List<Categorydb>>() {
                    public void onSuccess(List<Categorydb> result) {
                        data.postValue(result);
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                    }
                },
                //MainActivity.getAppContext().getMainExecutor()
                databaseExecutor
        );
        return data;
    }


    public void createCategory(Categorydb cat) {
        Futures.addCallback(
                db.CategoryDAO().insert(cat),
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

    public void setCategories() {
        /*dataSet.add(new Categorydb("Groceries", 10, 100,1));
        dataSet.add(new Categorydb("Work", 10, 100));
        dataSet.add(new Categorydb("School", 10, 100));
        dataSet.add(new Categorydb("QSDQDSQ", 120, 140));
        dataSet.add(new Categorydb("Buy a car", 0, 10000, true));
        dataSet.add(new Categorydb("Buy a cqdqdqar", 0, 10000, true));*/

    }

}
