package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

/**
 * ViewModel for AddSubCategoryViewModel
 */
public class AddSubCategoryDialogViewModel extends AndroidViewModel {
    private SubCategoriesRepository subCategoriesRepository;

    /**
     * Custom constructor
     *
     * @param application application
     */
    public AddSubCategoryDialogViewModel(@NonNull Application application) {
        super(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
    }

    /**
     * Invokes the insert subcategory query
     *
     * @param subCategory subcategory
     */
    public void insert(SubCategories subCategory) {
        this.subCategoriesRepository.insert(subCategory);
    }

    /**
     * Get sub-category id
     *
     * @return subcategory id
     */
    public long getCurrentCategoryId() {
        return CategoriesRepository.getCurrentCategory().getId();
    }
}
