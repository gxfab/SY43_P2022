package com.sucelloztm.sucelloz.ui.subcategories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.util.List;

/**
 * ViewModel for SubCategoriesFragment
 */
public class SubCategoriesViewModel extends AndroidViewModel {
    private SubCategoriesRepository subCategoriesRepository;

    private LiveData<List<SubCategories>> currentSubCategories;

    /**
     * Custom constructor
     *
     * @param application application
     */
    public SubCategoriesViewModel(@NonNull Application application) {
        super(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
    }

    /**
     * Invokes the getSubCategories Method from SubCategoriesRepository
     *
     * @return livedata of the list of all subcategories
     */
    public LiveData<List<SubCategories>> getSubCategories() {
        if (this.currentSubCategories == null) {
            this.currentSubCategories = subCategoriesRepository.getSubCategoriesFromCurrentCategoryId();
        }
        return this.currentSubCategories;
    }

    /**
     * invokes the deleteSubCategory Method from SubCategoriesRepository
     *
     * @param subCategory subcategory to delete
     */
    public void deleteSubCategory(SubCategories subCategory) {
        subCategoriesRepository.deleteSubCategory(subCategory);
    }

    /**
     * invokes the updateSubCategory Method from SubCategoriesRepository
     *
     * @param subCategory subcategory to delete
     */
    public void updateSubCategory(SubCategories subCategory) {
        subCategoriesRepository.updateSubCategory(subCategory);
    }
}
