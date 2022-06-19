package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;
import com.sucelloztm.sucelloz.database.DAO.CategoriesDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Categories;

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
     * @param category category to insert
     */
    public ListenableFuture<Void> insert(Categories category){
        return this.categoriesDao.insertCategory(category);
    }


    /**
     * invokes a query to get a category thanks to its name
     * @param categoryName name of the category
     * @return category
     */
    public LiveData<Categories> getCategoryByName(String categoryName) {
       return this.categoriesDao.getCategoryByName(categoryName);
    }

    /**
     * getter
     * @return current category
     */
    public static Categories getCurrentCategory() {
        return CategoriesRepository.currentCategory;
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
    public ListenableFuture<Void> deleteCategory(Categories category){
        return this.categoriesDao.deleteCategory(category);
    }

    /**
     * invokes the query to update a category
     * @param category category to update
     */
    public ListenableFuture<Void> updateCategory(Categories category){
        return this.categoriesDao.updateCategory(category);
    }

}


