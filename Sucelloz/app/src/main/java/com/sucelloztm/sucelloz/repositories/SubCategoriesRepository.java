package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.SubCategoriesDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentSum;

import java.util.List;

public class SubCategoriesRepository {
    private SubCategoriesDao subCategoriesDao;

    public SubCategoriesRepository(Application application){
        SucellozDatabase database= SucellozDatabase.getInstance(application);
        this.subCategoriesDao=database.subCategoriesDao();
    }

    public LiveData<List<SubCategories>> getSubCategoriesFromCurrentCategoryId(){
        return subCategoriesDao.getSubCategoriesWithCategoryId(CategoriesRepository.getCurrentCategory().getId());
    }

    public List<String> getSubCategoriesNames(){
        return subCategoriesDao.getSubCategoriesNames();
    }

    public SubCategories getSubCategoryWithName(String nameOfSubCategory){
        return subCategoriesDao.getSubCategoryWithName(nameOfSubCategory);
    }

    public void insert(SubCategories subCategory){
        subCategoriesDao.insertSubCategory(subCategory);
    }

    public String getSubCategoryNameWithId(long idOfSubCategory){
        return subCategoriesDao.getSubcategoryNameWithId(idOfSubCategory);
    }
    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithPositiveInfrequentSum(){
        return subCategoriesDao.getAllSubCategoriesWithPositiveInfrequentSum();
    }

    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithNegativeInfrequentSum(){
        return subCategoriesDao.getAllSubCategoriesWithNegativeInfrequentSum();
    }
}
