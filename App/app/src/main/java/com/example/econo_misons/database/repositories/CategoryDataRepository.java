package com.example.econo_misons.database.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.econo_misons.database.dao.categoryDAO;
import com.example.econo_misons.database.dao.userDAO;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.User;

import java.util.List;

public class CategoryDataRepository {

    private final categoryDAO catDao;

    public CategoryDataRepository(categoryDAO catDao) {this.catDao = catDao;}

    public LiveData<List<Category>> getAllCategories() {return this.catDao.getAllCategories();}

    public long addCategory(Category cat) {return this.catDao.addCategory(cat);}

    public void updateCategory(Category cat) {  this.catDao.updateCategory(cat);}

    public void deleteCategory(Category cat) {  this.catDao.deleteCategory(cat);}

    public LiveData<List<Category>> getCategoryByID(int id) {
        return this.catDao.getCategoryByID(id);
    }
}