package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;

public class AddCategoryDialogViewModel extends AndroidViewModel {
    private CategoriesRepository categoriesRepository;

    public AddCategoryDialogViewModel(@NonNull Application application) {
        super(application);
        categoriesRepository= new CategoriesRepository(application);
    }

    public void insert(Categories category){
        categoriesRepository.insert(category);
    }
}
