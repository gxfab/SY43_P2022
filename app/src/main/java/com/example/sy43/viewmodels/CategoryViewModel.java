package com.example.sy43.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sy43.CategoryActivity;
import com.example.sy43.models.Category;
import com.example.sy43.repositories.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private MutableLiveData<List<Category>> categories;
    private CategoryRepository catRepo;

    public void init() {
        if (categories != null) {
            return;
        }
        catRepo = CategoryRepository.getInstance();
        categories = catRepo.getCategories();
    }

    public LiveData<List<Category>> getCategories() {
        return categories;
    }
}
