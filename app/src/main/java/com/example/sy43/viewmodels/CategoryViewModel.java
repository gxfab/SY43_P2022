package com.example.sy43.viewmodels;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sy43.CategoryActivity;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.models.Category;
import com.example.sy43.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryViewModel extends ViewModel {
    private MutableLiveData<List<Categorydb>> categories;
    private MutableLiveData<List<Categorydb>> objectives;

    public CategoryRepository catRepo;

    public void init() {
        if (categories != null) {
            return;
        }

        catRepo = CategoryRepository.getInstance();
        categories = catRepo.getCategories();
        objectives = catRepo.getObjectives();

    }

    public void createCategory(final Categorydb category) {
        this.catRepo.createCategory(category);
        this.categories.postValue(this.categories.getValue());
    }

    public void delCategories(int id) {
        this.catRepo.deleteCategory(id);
    }

    public LiveData<Categorydb> getCategoryById(int id){
        return this.catRepo.getCategoryById(id);
    }
    public LiveData<List<Categorydb>> getCategories() {
        return categories;
    }
    public LiveData<List<Categorydb>> getObjectives() {
        return objectives;
    }
}
