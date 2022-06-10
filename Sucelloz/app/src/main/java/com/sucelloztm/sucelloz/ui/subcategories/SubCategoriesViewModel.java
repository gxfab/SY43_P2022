package com.sucelloztm.sucelloz.ui.subcategories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.util.List;

public class SubCategoriesViewModel extends AndroidViewModel {
    private SubCategoriesRepository subCategoriesRepository;

    private LiveData<List<SubCategories>> currentSubCategories;

    public SubCategoriesViewModel(@NonNull Application application) {
        super(application);
        this.subCategoriesRepository= new SubCategoriesRepository(application);
    }

    public LiveData<List<SubCategories>> getSubCategories(){
        if (this.currentSubCategories == null){
            this.currentSubCategories = subCategoriesRepository.getSubcategoriesFromCurrentCategoryId();
        }
        return this.currentSubCategories;
    }
}
