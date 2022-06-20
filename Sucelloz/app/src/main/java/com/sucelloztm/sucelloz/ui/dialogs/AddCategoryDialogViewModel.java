package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;

public class AddCategoryDialogViewModel extends AndroidViewModel {
    private CategoriesRepository categoriesRepository;

    /**
     * custom constructor
     * @param application application
     */
    public AddCategoryDialogViewModel(@NonNull Application application) {
        super(application);
        this.categoriesRepository= new CategoriesRepository(application);
    }

    /**
     * invokes the query to insert a category
     * @param category category
     */
    public void insert(Categories category){
        this.categoriesRepository.insert(category);
    }
}
