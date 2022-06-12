package com.example.sy43.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.sy43.MainActivity;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.db.mainDB.DB;
import com.example.sy43.db.mainDB.DBexec;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryRepository {
    private static SubCategoryRepository instance;
    private DB db;
    private DBexec databaseExecutor;

    public static SubCategoryRepository getInstance() {
        if (instance == null) instance = new SubCategoryRepository();
        return instance;
    }

    SubCategoryRepository() {
        db = DB.getAppDatabase(MainActivity.getAppContext());
        databaseExecutor = DBexec.getExecutor();
    }

    public MutableLiveData<List<SubCategory>> getSubCategoriesByCatId(int id) {
        MutableLiveData<List<SubCategory>> data = new MutableLiveData<>();

        Futures.addCallback(
                db.SubCategoryDAO().findByCategoryID(id),
                new FutureCallback<List<SubCategory>>() {
                    public void onSuccess(List<SubCategory> result) {
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

    public void createSubCategory(SubCategory subCategory) {
        Futures.addCallback(
                db.SubCategoryDAO().insert(subCategory),
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

    public void deleteSubCategory(int id){
        Futures.addCallback(
                db.SubCategoryDAO().delSubByID(id),
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
