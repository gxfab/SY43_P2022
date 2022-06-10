package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.SubCategoriesDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.SubCategories;

import java.util.List;

public class SubCategoriesRepository {
    private SubCategoriesDao subCategoriesDao;

    public SubCategoriesRepository(Application application){
        SucellozDatabase database= SucellozDatabase.getInstance(application);
        this.subCategoriesDao=database.subCategoriesDao();
    }

    public LiveData<List<SubCategories>> getSubcategoriesFromCurrentCategoryId(){
        return subCategoriesDao.getSubCategoriesWithCategoryId(CategoriesRepository.getCurrentCategory().getId());
    }

    public void insert(SubCategories subCategory){
        subCategoriesDao.insertSubCategory(subCategory);
    }
}
