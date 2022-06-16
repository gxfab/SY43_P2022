package com.sucelloztm.sucelloz.repositories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.sucelloztm.sucelloz.database.DAO.CategoriesDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.CategoriesWithSubCategoriesWithInfrequentSum;
import com.sucelloztm.sucelloz.repositories.MainRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * class for the categories repository
 */
public class CategoriesRepository {
    private CategoriesDao categoriesDao;
    //Used in order to know which subcategories to show in the recyclerview of SubCategoriesFragment
    private static Categories currentCategory;

    /**
     * custom constructor
     * @param application current application
     */
    public CategoriesRepository(Application application){
        SucellozDatabase database=SucellozDatabase.getInstance(application);
        this.categoriesDao=database.categoriesDao();
    }

    /**
     * invokes the query to get a livedata of all categories
     * @return livedata of the categories
     */
    public LiveData<List<Categories>> getAllCategories(){
        return categoriesDao.getAllCategories();
    }

    /**
     * invokes the query to insert a category
     * @param category categiry to insert
     */
    public void insert(Categories category){
        categoriesDao.insertCategory(category);
    }


    /**
     * invokes a query to get a category thanks to its name
     * @param categoryName name of the category
     * @return categiry
     */
    public Categories getCategoryByName(String categoryName) {
       return this.categoriesDao.getCategoryByName(categoryName);
    }

    /**
     * getter
     * @return current category
     */
    public static Categories getCurrentCategory() {
        return currentCategory;
    }

    /**
     * setter
     * @param currentCategory new current category
     */
    public static void setCurrentCategory(Categories currentCategory) {
        CategoriesRepository.currentCategory = currentCategory;
    }

    /**
     * invokes the query to delete a category
     * @param category category to delete
     */
    public void deleteCategory(Categories category){
        this.categoriesDao.deleteCategory(category);
    }

    /**
     * invokes the query to update a category
     * @param category category to update
     */
    public void updateCategory(Categories category){
        this.categoriesDao.updateCategory(category);
    }

}


