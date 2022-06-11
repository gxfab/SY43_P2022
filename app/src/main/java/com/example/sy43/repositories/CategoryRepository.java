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

    public static CategoryRepository getInstance() {
        if (instance == null) instance = new CategoryRepository();
        return instance;
    }

    // For now we fake the query
    public MutableLiveData<List<Categorydb>> getCategories() {
        DB db = DB.getAppDatabase(MainActivity.getAppContext());
        DBexec databaseExecutor = DBexec.getExecutor();
        MutableLiveData<List<Categorydb>> data = new MutableLiveData<>();

        Futures.addCallback(
                db.CategoryDAO().findAll(),
                new FutureCallback<List<Categorydb>>() {
                    public void onSuccess(List<Categorydb> result) {
                        Log.d("Test", String.valueOf(result.get(0)));
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

    public void addCategory(String name) {
        //this.dataSet.add(new Categorydb(name, 10, 20));
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
