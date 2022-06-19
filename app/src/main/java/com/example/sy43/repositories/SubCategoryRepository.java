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


    /**
     * Renvoi l'instance de SubCategoryRepository
     *
     * @return l'instance de SubCategoryRepository
     */

    public static SubCategoryRepository getInstance() {
        if (instance == null) instance = new SubCategoryRepository();
        return instance;
    }

    SubCategoryRepository() {
        db = DB.getAppDatabase(MainActivity.getAppContext());
        databaseExecutor = DBexec.getExecutor();
    }
    /**
     * Retourne toutes les sous catégories de la BDD
     *
     * @return  MutableLiveData<List<SubCategory>> liste des sous catégories
     */
    public MutableLiveData<List<SubCategory>> getSubCategories() {
        MutableLiveData<List<SubCategory>> data = new MutableLiveData<>();

        Futures.addCallback(
                db.SubCategoryDAO().findAll(),
                new FutureCallback<List<SubCategory>>() {
                    public void onSuccess(List<SubCategory> result) {
                        data.postValue(result);

                    }

                    public void onFailure(@NonNull Throwable thrown) {
                        Log.d("Test", String.valueOf(thrown));
                    }
                },
                databaseExecutor
        );
        return data;
    }

    /**
     * Retourne une sous catégorie via son id
     *
     * @param id id de la sub catégorie sous forme d'int
     */
    public MutableLiveData<SubCategory> getSubCategoryById(int id) {
        MutableLiveData<SubCategory> data = new MutableLiveData<>();

        Futures.addCallback(
                db.SubCategoryDAO().findByID(id),
                new FutureCallback<SubCategory>() {
                    public void onSuccess(SubCategory result) {
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
     * Retourne tous les sous objectifs de la catégorie avec l'id passée en paramètre
     *
     * @param id id de la catégorie
     * @return MutableLiveData<List<SubCategory>> Liste des sous catégories trouvées
     */

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

    /**
     * Crée une nouvelle sub catégorie.
     *
     * @param SubCategory sub catégorie à créer
     */

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
    /**
     * Delete une catégorie
     *
     * @param id id de la catégorie à delete
     */
    public void deleteSubCategory(int id){
        Futures.addCallback(
                db.SubCategoryDAO().delSubByID(id),
                new FutureCallback<Void>() {
                    @Override
                    public void onSuccess(Void stp) {
                        Log.d("Test10", "Working!");
                    }

                    public void onFailure(@NonNull Throwable thrown) {
                        Log.d("Test10", String.valueOf(thrown));
                    }
                },
                databaseExecutor
        );
    }

}
