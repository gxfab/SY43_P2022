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

    public void insert(SubCategory subCategory){
        mRepository.insert(subCategory);
    }

    public void delete(SubCategory subCategory){
        mRepository.delete(subCategory);
    }

    public void update(String catName, String subcatName, int id) {
        mRepository.update(catName, subcatName, id);
    }
}
