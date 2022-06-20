package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.CategoriesDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Categories;

import java.util.List;

/**
 * Class for the categories repository
 */
public class CategoriesRepository {
    private CategoriesDao categoriesDao;
    //Used in order to know which subcategories to show in the recyclerview of SubCategoriesFragment
    private static Categories currentCategory;

    /**
     * Custom constructor
     * @param application current application in which this constructor is used
     */
    public CategoriesRepository(Application application){
        SucellozDatabase database=SucellozDatabase.getInstance(application);
        this.categoriesDao=database.categoriesDao();
    }

    /**
     * Invokes the query to get a livedata of all categories
     * @return livedata of the list of categories
     */
    public LiveData<List<Categories>> getAllCategories(){
        return categoriesDao.getAllCategories();
    }

    /**
     * Invokes the query to insert a category
     * @param category categiry to insert
     */
    public void insert(Categories category){
        categoriesDao.insertCategory(category);
    }


    /**
     * Invokes a query to get a category thanks to its name
     * @param categoryName name of the category
     * @return category
     */
    public Categories getCategoryByName(String categoryName) {
       return this.categoriesDao.getCategoryByName(categoryName);
    }

    /**
     * Getter
     * @return current category
     */
    public static Categories getCurrentCategory() {
        return currentCategory;
    }

    /**
     * Setter
     * @param currentCategory new current category to set
     */
    public static void setCurrentCategory(Categories currentCategory) {
        CategoriesRepository.currentCategory = currentCategory;
    }

    /**
     * Invokes the query to delete a category
     * @param category category to delete
     */
    public void deleteCategory(Categories category){
        this.categoriesDao.deleteCategory(category);
    }

    /**
     * Invokes the query to update a category
     * @param category category to update
     */
    public void updateCategory(Categories category){
        this.categoriesDao.updateCategory(category);
    }

}


