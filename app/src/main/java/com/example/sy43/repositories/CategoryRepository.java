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


    /**
     * Renvoi l'instance de CategoryRepository
     *
     * @return l'instance de CategoryRepository
     */
    public static CategoryRepository getInstance() {
        if (instance == null) instance = new CategoryRepository();
        return instance;
    }


    CategoryRepository() {
        db = DB.getAppDatabase(MainActivity.getAppContext());
        databaseExecutor = DBexec.getExecutor();
    }


    /**
     * Met à jour une catégoie dans la BDD via la DAO
     *
     * @param category la catégorie modifiée
     */
    public void updateCategory(Categorydb category) {
        Futures.addCallback(
                db.CategoryDAO().update(category),
                new FutureCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {

                    }

                    public void onFailure(@NonNull Throwable thrown) {
                    }
                },
                databaseExecutor
        );
    }


    /**
     * Retourne une catégorie via son id
     *
     * @param categoryId id de la catégorie sous forme d'int
     */
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


    /**
     * Retourne toutes les catégories de la BDD
     *
     * @return  MutableLiveData<List<Categorydb>> liste des catégories
     */
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

    /**
     * Retourne tous les objectifs de la BDD
     * Dans le programme, un objectif est traitée comme une catégorie.
     *
     * @return  MutableLiveData<List<Categorydb>> liste des objectifs
     */
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

    /**
     * Crée une nouvelle catégorie.
     *
     * @param Categorydb catégorie à créer
     */
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
    /**
     * Delete une catégorie
     *
     * @param id id de la sub catégorie à delete
     */
    public void deleteCategory(int id){
        Futures.addCallback(
                db.CategoryDAO().delByID(id),
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
