package com.sucelloztm.sucelloz.ui.categories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;

import java.util.List;

/**
 * ViewModel for Categories
 */
public class CategoriesViewModel extends AndroidViewModel {
    private CategoriesRepository categoriesRepository;

    private LiveData<List<Categories>> currentCategories;

    /**
     * Constructor of CategoriesViewModel Class
     * @param application instance of Application
     */
    public CategoriesViewModel(Application application) {
        super(application);
        this.categoriesRepository = new CategoriesRepository(application);
    }

    /**
     * Get a LiveDate list of Categories
     * @return currentCategories a LiveData<List<Categories>>
     */
    public LiveData<List<Categories>> getAllCategories() {
        if (this.currentCategories == null) {
            this.currentCategories = categoriesRepository.getAllCategories();
        }
        return this.currentCategories;
    }

    /**
     * Get a category by it's name
     * @param categoryName a String name of the category to search
     * @return category Category found
     */
    public Categories getCategoryByName(String categoryName){
        return this.categoriesRepository.getCategoryByName(categoryName);
    }

    /**
     * Set current category as given category
     * @param category given category
     */
    public void setCurrentCategory(Categories category){
        CategoriesRepository.setCurrentCategory(category);
    }

    /**
     * Delete given category
     * @param category given category
     */
    public void deleteCategory(Categories category){
        this.categoriesRepository.deleteCategory(category);
    }

    /**
     * Update given category
     * @param category given category
     */
    public void updateCategory(Categories category){
        this.categoriesRepository.updateCategory(category);
    }
}
