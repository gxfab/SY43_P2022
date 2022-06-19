package com.sucelloztm.sucelloz.ui.categories;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * view model for the categories
 */
public class CategoriesViewModel extends AndroidViewModel {
    private CategoriesRepository categoriesRepository;

    private LiveData<List<Categories>> currentCategories;
    private ExecutorService executor;

    /**
     * custom constructor
     * @param application application
     */
    public CategoriesViewModel(Application application) {
        super(application);
        this.categoriesRepository = new CategoriesRepository(application);
        executor = Executors.newSingleThreadExecutor();
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

    public LiveData<Categories> getCategoryByName(String categoryName){
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
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoriesRepository.deleteCategory(category);
            }
        });
    }

    /**
     * invokes the query to update a category
     * @param category category
     */
    public void updateCategory(Categories category){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoriesRepository.updateCategory(category);
            }
        });
    }
}
