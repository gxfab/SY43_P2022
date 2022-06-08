package com.sucelloztm.sucelloz.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.sucelloztm.sucelloz.database.DAO.CategoriesDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.repositories.MainRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository {
    private CategoriesDao categoriesDao;

    public CategoriesRepository(Application application){
        SucellozDatabase database=SucellozDatabase.getInstance(application);
        this.categoriesDao=database.categoriesDao();
    }

    public LiveData<List<Categories>> getAllCategories(){
        return categoriesDao.getAllCategories();
    }

    public void insert(Categories category){
        categoriesDao.insertCategory(category);
    }
}


