package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * class to add a subcategory dialog to a view model
 */
public class AddSubCategoryDialogViewModel extends AndroidViewModel {
    private SubCategoriesRepository subCategoriesRepository;
    private ExecutorService executor;

    /**
     * custom constructor
     * @param application application
     */
    public AddSubCategoryDialogViewModel(@NonNull Application application) {
        super(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);
        executor= Executors.newSingleThreadExecutor();
    }

    /**
     * invokes the insert subcategory query
     * @param subCategory subcategory
     */
    public void insert(SubCategories subCategory){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                subCategoriesRepository.insert(subCategory);
            }
        });
    }

    /**
     * getter
     * @return subcategory id
     */
    public long getCurrentCategoryId(){
        return CategoriesRepository.getCurrentCategory().getId();
    }
}
