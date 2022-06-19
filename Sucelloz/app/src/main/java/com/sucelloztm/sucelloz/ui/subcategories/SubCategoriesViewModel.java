package com.sucelloztm.sucelloz.ui.subcategories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.util.List;

/**
 * view model for the subcategories
 */
public class SubCategoriesViewModel extends AndroidViewModel {
    private SubCategoriesRepository subCategoriesRepository;

    private LiveData<List<SubCategories>> currentSubCategories;

    /**
     * custom constructor
     * @param application application
     */
    public SubCategoriesViewModel(@NonNull Application application) {
        super(application);
        this.subCategoriesRepository= new SubCategoriesRepository(application);
    }

    /**
     * invokes the get subcategories query
     * @return livedata of the subcategories
     */
    public LiveData<List<SubCategories>> getSubCategories(){
        if (this.currentSubCategories == null){
            this.currentSubCategories = subCategoriesRepository.getSubCategoriesFromCurrentCategoryId();
        }
        return this.currentSubCategories;
    }

    /**
     * invokes the delete subcategory query
     * @param subCategory subcategory
     */
    public void deleteSubCategory(SubCategories subCategory){
        subCategoriesRepository.deleteSubCategory(subCategory);
    }

    /**
     * invokes the delete subcategory query
     * @param subCategory subcategory
     */
    public void updateSubCategory(SubCategories subCategory){
        subCategoriesRepository.updateSubCategory(subCategory);
    }
}
