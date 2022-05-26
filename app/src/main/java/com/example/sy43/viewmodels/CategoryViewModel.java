package com.example.sy43.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sy43.CategoryActivity;
import com.example.sy43.models.Category;
import com.example.sy43.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryViewModel extends ViewModel {
    private MutableLiveData<List<Category>> categories;
    public CategoryRepository catRepo;

    public void init() {
        if (categories != null) {
            return;
        }
        catRepo = CategoryRepository.getInstance();
        categories = catRepo.getCategories();
    }

    public void addNewCategory(final Category category) {
        // TODO: une fois la DAO fini, faire une requête vers l'api au lieu d'ajouter en dur
        List<Category> currentCategories = this.categories.getValue();
        currentCategories.add(category);
        categories.postValue(currentCategories);
    }
    public LiveData<List<Category>> getCategories() {
        return categories;
    }
}
