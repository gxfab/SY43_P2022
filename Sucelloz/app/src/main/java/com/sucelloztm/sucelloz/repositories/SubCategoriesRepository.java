package com.sucelloztm.sucelloz.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.sucelloztm.sucelloz.database.DAO.SubCategoriesDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.models.SubCategoriesWithInfrequentSum;

import java.util.List;

/**
 * Class for the subcategory repository
 */
public class SubCategoriesRepository {
    private SubCategoriesDao subCategoriesDao;
    private static SubCategories currentSubCategory;

    /**
     * Custom constructor
     *
     * @param application current application
     */
    public SubCategoriesRepository(Application application) {
        SucellozDatabase database = SucellozDatabase.getInstance(application);
        this.subCategoriesDao = database.subCategoriesDao();
    }

    /**
     * Invokes a query to get all subcategories belonging to a category
     *
     * @return subcategories with the searched category id
     */
    public LiveData<List<SubCategories>> getSubCategoriesFromCurrentCategoryId() {
        return subCategoriesDao.getSubCategoriesWithCategoryId(CategoriesRepository.getCurrentCategory().getId());
    }

    /**
     * Invokes a query to get the names of the subcategories
     *
     * @return list of the names of the subcatories
     */
    public List<String> getSubCategoriesNames() {
        return subCategoriesDao.getSubCategoriesNames();
    }

    /**
     * Invokes a query to get a subcategory thanks to its name
     *
     * @param nameOfSubCategory name of the searched subcategory
     * @return searched category
     */
    public SubCategories getSubCategoryWithName(String nameOfSubCategory) {
        return subCategoriesDao.getSubCategoryWithName(nameOfSubCategory);
    }

    /**
     * Invokes a query to insert a subcategory
     *
     * @param subCategory subcategory to insert
     */
    public void insert(SubCategories subCategory) {
        subCategoriesDao.insertSubCategory(subCategory);
    }

    /**
     * Invokes a query to get the name of a subcategory thanks to its id
     *
     * @param idOfSubCategory id
     * @return name
     */
    public String getSubCategoryNameWithId(long idOfSubCategory) {
        return subCategoriesDao.getSubcategoryNameWithId(idOfSubCategory);
    }

    /**
     * Invokes a query to get all subcategories with their respective positive sum
     *
     * @return livedata of a list of all subcategories with their respective positive sum
     */
    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithPositiveInfrequentSum() {
        return subCategoriesDao.getAllSubCategoriesWithPositiveInfrequentSum();
    }

    /**
     * Invokes a query to get all subcategories with their respective negative sum
     *
     * @return livedata of a list of all subcategories with their respective positive su
     */
    public LiveData<List<SubCategoriesWithInfrequentSum>> getAllSubCategoriesWithNegativeInfrequentSum() {
        return subCategoriesDao.getAllSubCategoriesWithNegativeInfrequentSum();
    }

    /**
     * Invokes the query that deletes a sub-category
     *
     * @param subCategory subcategory to delete
     */
    public void deleteSubCategory(SubCategories subCategory) {
        subCategoriesDao.deleteSubCategory(subCategory);
    }

    /**
     * Invokes the query that updates a sub-category
     *
     * @param subCategory subcategory to updates
     */
    public void updateSubCategory(SubCategories subCategory) {
        subCategoriesDao.updateSubCategory(subCategory);
    }

    /**
     * Getter
     *
     * @return currentSubCategory
     */
    public static SubCategories getCurrentSubCategory() {
        return currentSubCategory;
    }

    /**
     * Setter
     *
     * @param currentSubCategory
     */
    public static void setCurrentSubCategory(SubCategories currentSubCategory) {
        SubCategoriesRepository.currentSubCategory = currentSubCategory;
    }
}
