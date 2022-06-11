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

    public LiveData<Integer> getPercentUsedOf(SubCategory subCategory){
        return this.mRepository.getPercentUsedOf(subCategory.getM_SUBCAT_ID());
    }
}
