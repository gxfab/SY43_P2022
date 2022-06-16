package com.example.nomoola.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.database.repository.DataRepository;

import java.util.List;

public class SubcategoryViewModel extends AndroidViewModel {

    private final DataRepository mRepository;
    private final LiveData<List<SubCategory>> mAllSubCategories;

    public SubcategoryViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new DataRepository(application);
        this.mAllSubCategories = mRepository.getAllSubCategories();
    }

    public LiveData<List<SubCategory>> getmAllSubCategories(){
        return mAllSubCategories;
    }

    public LiveData<List<SubCategory>> getSubCategoriesOf(int categoryID){
        return mRepository.getSubCategoriesOf(categoryID);
    }

    public void insert(SubCategory subCategory){
        mRepository.insert(subCategory);
    }

    public void delete(SubCategory subCategory){
        mRepository.delete(subCategory);
    }

    public void update(int catID, String subcatName, int id) {
        mRepository.update(catID, subcatName, id);
    }

    public LiveData<Integer> getPercentUsedBySubcategory(SubCategory subCategory){
        return this.mRepository.getPercentUsedOf(subCategory.getM_SUBCAT_ID());
    }

    public LiveData<Integer> getPercentUsedOfCategory(Category category){
        return this.mRepository.getPercentUsedOfCategory(category.getM_CAT_ID());
    }

    public LiveData<Double> getCategoryBudget(Category category){
        return this.mRepository.getBudgetOf(category.getM_CAT_ID());
    }

    public LiveData<Double> getBudgetLeftOf(Category category) {
        return this.mRepository.getBudgetLeftOf(category.getM_CAT_ID());
    }

    public LiveData<Double> getAmountUsedBySubcategory(SubCategory subCategory) {
        return this.mRepository.getAmountUsedBySubcategory(subCategory.getM_SUBCAT_ID());
    }
}
