package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;
import com.sucelloztm.sucelloz.database.DAO.SubCategoriesDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentSum;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

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
    public LiveData<List<SubCategories>> getSubCategoriesFromCurrentCategory(){
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
    public LiveData<SubCategories> getSubCategoryWithName(String nameOfSubCategory){
        return subCategoriesDao.getSubCategoryWithName(nameOfSubCategory);
    }

    /**
     * invokes a query to insert a subcategory
     * @param subCategory subcategory to insert
     */
    public ListenableFuture<Void> insert(SubCategories subCategory){
        return subCategoriesDao.insertSubCategory(subCategory);
    }

    /**
     * invokes a query to get the name of a subcategory thanks to its id
     * @param idOfSubCategory id
     * @return name
     */
    public LiveData<String> getSubCategoryNameWithId(long idOfSubCategory){
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

    public  ListenableFuture<Void> updateSubCategory(SubCategories subCategory){
        return subCategoriesDao.updateSubCategory(subCategory);
    }

    public ListenableFuture<Void> deleteSubCategory(SubCategories subCategory){
        return subCategoriesDao.deleteSubCategory(subCategory);
    }


    public static SubCategories getCurrentSubCategory() {
        return currentSubCategory;
    }

    public static void setCurrentSubCategory(SubCategories currentSubCategory) {
        SubCategoriesRepository.currentSubCategory = currentSubCategory;
    }

    public LiveData<List<SubCategories>> getSubCategoriesWithCategoryId(long id){
        return subCategoriesDao.getSubCategoriesWithCategoryId(id);
    }
}
