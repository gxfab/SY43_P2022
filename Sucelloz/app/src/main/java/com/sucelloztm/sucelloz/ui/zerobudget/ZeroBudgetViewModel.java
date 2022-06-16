package com.sucelloztm.sucelloz.ui.zerobudget;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;
import com.sucelloztm.sucelloz.repositories.SubCategoriesRepository;

public class ZeroBudgetViewModel extends AndroidViewModel {
    private final MutableLiveData<String> mText;
    private SubCategoriesRepository subCategoriesRepository;

    public ZeroBudgetViewModel(Application application) {
        super(application);
        this.subCategoriesRepository = new SubCategoriesRepository(application);

        mText = new MutableLiveData<>();
        mText.setValue("This is Zero Budget fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public SubCategories getSubCategoryByName(String name){
        return this.subCategoriesRepository.getSubCategoryWithName(name);
    }

}

