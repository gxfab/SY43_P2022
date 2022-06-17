package com.example.nomoola.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.repository.DataRepository;
import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private final DataRepository mRepository;
    private final LiveData<List<Category>> mAllCategories;


    public CategoryViewModel(@NonNull Application application) {
        super(application);
        Log.d("CREATION", "Instantiation of CategoryViewModel");
        this.mRepository = new DataRepository(application);
        this.mAllCategories = mRepository.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories(){
        return mAllCategories;
    }

    public void insert(Category category){
        mRepository.insert(category);
    }

    public void delete(Category category){
        mRepository.delete(category);
    }

    public void update(String name, double budgetAmount, int id) {
        mRepository.update(name, budgetAmount, id);
    }

    public LiveData<Double> getBudgetLeftOf(Category category){
        return this.mRepository.getBudgetLeftOf(category.getM_CAT_ID());
    }

    public LiveData<Integer> getPercentUsedOfCategory(Category category){
        return this.mRepository.getPercentUsedOfCategory(category.getM_CAT_ID());
    }

    public LiveData<List<Category>> getCategoriesOfType(Category.CategoryType type){
        return this.mRepository.getCategoriesOfType(type);
    }

    public void update(Category category) {
        this.mRepository.update(category);
    }

    public LiveData<String> getUserName(int userID){
        return this.mRepository.getUserName(userID);
    }
}
