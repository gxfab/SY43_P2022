package com.sucelloztm.sucelloz.ui.categories;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;

import java.util.List;

public class CategoriesViewModel extends AndroidViewModel {
    private CategoriesRepository categoriesRepository;


    public CategoriesViewModel(Application application) {
        super(application);
        categoriesRepository = new CategoriesRepository(application);
    }

    public List<Categories> getAllCategories() {
        return categoriesRepository.getAllCategories();
    }

    public void insert(Categories category){
        categoriesRepository.insert(category);
    }
}
