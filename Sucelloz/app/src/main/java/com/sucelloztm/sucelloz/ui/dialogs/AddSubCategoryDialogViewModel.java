package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

/**
 * class to add a subcategory dialog to a view model
 */
public class AddSubCategoryDialogViewModel extends AndroidViewModel {
    private SubCategoriesRepository subCategoriesRepository;

    /**
     * custom constructor
     * @param application application
     */
    public AddSubCategoryDialogViewModel(@NonNull Application application) {
        super(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
    }

    /**
     * invokes the insert subcategory query
     * @param subCategory subcategory
     */
    public void insert(SubCategories subCategory){
        this.subCategoriesRepository.insert(subCategory);
    }

    /**
     * getter
     * @return subcategory id
     */
    public long getCurrentCategoryId(){
        return CategoriesRepository.getCurrentCategory().getId();
    }
}
