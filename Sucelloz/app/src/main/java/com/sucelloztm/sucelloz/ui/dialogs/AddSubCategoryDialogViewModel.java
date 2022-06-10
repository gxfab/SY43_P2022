package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

public class AddSubCategoryDialogViewModel extends AndroidViewModel {
    private SubCategoriesRepository subCategoriesRepository;
    public AddSubCategoryDialogViewModel(@NonNull Application application) {
        super(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
    }

    public void insert(SubCategories subCategory){
        this.subCategoriesRepository.insert(subCategory);
    }

    public long getCurrentCategoryId(){
        return CategoriesRepository.getCurrentCategory().getId();
    }
}
