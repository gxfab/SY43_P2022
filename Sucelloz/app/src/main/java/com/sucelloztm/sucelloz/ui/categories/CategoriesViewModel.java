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

public class CategoriesViewModel extends AndroidViewModel {
    private CategoriesRepository categoriesRepository;

    private LiveData<List<Categories>> currentCategories;
    private ExecutorService executor;

    public CategoriesViewModel(Application application) {
        super(application);
        this.categoriesRepository = new CategoriesRepository(application);
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Categories>> getAllCategories() {
        if (this.currentCategories == null) {
            this.currentCategories = categoriesRepository.getAllCategories();
        }
        return this.currentCategories;
    }

    public LiveData<Categories> getCategoryByName(String categoryName){
        return this.categoriesRepository.getCategoryByName(categoryName);
    }

    public void setCurrentCategory(Categories category){
        CategoriesRepository.setCurrentCategory(category);
    }

    public void deleteCategory(Categories category){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoriesRepository.deleteCategory(category);
            }
        });
    }

    public void updateCategory(Categories category){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoriesRepository.updateCategory(category);
            }
        });
    }
}
