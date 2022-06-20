package com.example.econo_misons.database.repositories;

import androidx.lifecycle.LiveData;

import com.example.econo_misons.database.dao.categoryDAO;
import com.example.econo_misons.database.models.Category;

import java.util.List;

//The functions called by the DBViewModel on a new thread.
//The comments for the functions are in the DBViewModel file if they aren't here
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