package com.example.sy43.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private DatabaseRepository repository;
    private LiveData<List<SubCategory>> allSubCategories;

     public ViewModel(@NonNull Application application) {
        super(application);
         repository = new DatabaseRepository(application);
         allSubCategories = repository.getAllsubcategories();
    }

    public void insert(SubCategory subCategory){
         repository.insert(subCategory);
    }
    public void update(SubCategory subCategory){
         repository.update(subCategory);
    }
    public void delete(SubCategory subCategory){
         repository.delete(subCategory);
    }

    public LiveData<List<SubCategory>> getAllSubCategories(){
         return allSubCategories;
    }
}
