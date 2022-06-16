package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.SubCategoriesDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentSum;

import java.util.List;

/**
 * class for the subcategory repository
 */
public class SubCategoriesRepository {
    private SubCategoriesDao subCategoriesDao;
    private static SubCategories currentSubCategory;

    /**
     * custom constructor
     * @param application current application
     */
    public SubCategoriesRepository(Application application){
        SucellozDatabase database= SucellozDatabase.getInstance(application);
        this.subCategoriesDao=database.subCategoriesDao();
    }

    /**
     * invokes a query to get all subcategories belonging to a category
     * @return subcategories with the searched category id
     */
    public LiveData<List<SubCategories>> getSubCategoriesFromCurrentCategoryId(){
        return subCategoriesDao.getSubCategoriesWithCategoryId(CategoriesRepository.getCurrentCategory().getId());
    }

    /**
     * invokes a query to get the names of the subcategories
     * @return list of the names of the subcatories
     */
    public List<String> getSubCategoriesNames(){
        return subCategoriesDao.getSubCategoriesNames();
    }

    /**
     * invokes a query to get a subcategory thanks to its name
     * @param nameOfSubCategory name of the searched subcategory
     * @return searched category
     */
    public SubCategories getSubCategoryWithName(String nameOfSubCategory){
        return subCategoriesDao.getSubCategoryWithName(nameOfSubCategory);
    }

    /**
     * invokes a query to insert a subcategory
     * @param subCategory subcategory to insert
     */
    public void insert(SubCategories subCategory){
        subCategoriesDao.insertSubCategory(subCategory);
    }

    /**
     * invokes a query to get the name of a subcategory thanks to its id
     * @param idOfSubCategory id
     * @return name
     */
    public String getSubCategoryNameWithId(long idOfSubCategory){
        return subCategoriesDao.getSubcategoryNameWithId(idOfSubCategory);
    }

    /**
     * invokes a query to get all subcategories with a positive sum
     * @return livedata of the subcategories
     */
    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithPositiveInfrequentSum(){
        return subCategoriesDao.getAllSubCategoriesWithPositiveInfrequentSum();
    }

    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithNegativeInfrequentSum(){
        return subCategoriesDao.getAllSubCategoriesWithNegativeInfrequentSum();
    }

    public void deleteSubCategory(SubCategories subCategory){
        subCategoriesDao.deleteSubCategory(subCategory);
    }

    public void updateSubCategory(SubCategories subCategory){
        subCategoriesDao.updateSubCategory(subCategory);
    }

    public SubCategories getCurrentSubCategory() {
        return currentSubCategory;
    }

    public void setCurrentSubCategory(SubCategories currentSubCategory) {
        SubCategoriesRepository.currentSubCategory = currentSubCategory;
    }
}
