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
 * view model for the categories
 */
public class CategoriesViewModel extends AndroidViewModel {
    private CategoriesRepository categoriesRepository;

    private LiveData<List<Categories>> currentCategories;

    /**
     * custom constructor
     * @param application application
     */
    public CategoriesViewModel(Application application) {
        super(application);
        this.categoriesRepository = new CategoriesRepository(application);
    }

    /**
     * invokes the query to get all categories
     * @return livedata of the catgeories
     */
    public LiveData<List<Categories>> getAllCategories() {
        if (this.currentCategories == null) {
            this.currentCategories = categoriesRepository.getAllCategories();
        }
        return this.currentCategories;
    }

    /**
     * invokes the query to get a category by its name
     * @param categoryName name of the category
     * @return searched category
     */
    public Categories getCategoryByName(String categoryName){
        return this.categoriesRepository.getCategoryByName(categoryName);
    }

    /**
     * setter
     * @param category category
     */
    public void setCurrentCategory(Categories category){
        CategoriesRepository.setCurrentCategory(category);
    }

    /**
     * invokes the query to delete a category
     * @param category category
     */
    public void deleteCategory(Categories category){
        this.categoriesRepository.deleteCategory(category);
    }

    /**
     * invokes the query to update a category
     * @param category category
     */
    public void updateCategory(Categories category){
        this.categoriesRepository.updateCategory(category);
    }
}
