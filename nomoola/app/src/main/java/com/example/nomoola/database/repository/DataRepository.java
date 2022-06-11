package com.example.nomoola.database.repository;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.room.Insert;

import com.example.nomoola.database.dao.CategoryDAO;
import com.example.nomoola.database.dao.InOutComeDAO;
import com.example.nomoola.database.dao.SubCategoryDAO;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.database.roomDataBase.NomoolaRoomDataBase;

import java.time.LocalDate;
import java.util.List;

public class DataRepository {

    private CategoryDAO mCategoryDAO;
    private LiveData<List<Category>> mAllCategory;

    private SubCategoryDAO mSubCategoryDAO;
    private LiveData<List<SubCategory>> mAllSubCategory;

    private InOutComeDAO mInOutComeDAO;
    private LiveData<List<InOutCome>> mAllInOutCome;

    public DataRepository(Application application) {
        Log.d("CREATION", "Instantiation of CategoryRepository");
        NomoolaRoomDataBase db = NomoolaRoomDataBase.getDatabase(application);

        mCategoryDAO = db.categoryDAO();
        mAllCategory = mCategoryDAO.getAllCategories();

        mSubCategoryDAO = db.subCategoryDAO();
        mAllSubCategory = mSubCategoryDAO.getAllSubCategories();

        mInOutComeDAO = db.inOutComeDAO();
        mAllInOutCome = mInOutComeDAO.getALlInOutComes();
    }

    /*
        CATEGORY
     */

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategory;
    }
    public void insert(Category category) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mCategoryDAO.insertCategory(category);
        });
    }
    public void delete(Category category) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mCategoryDAO.deleteCategory(category);
        });
    }
    public void update(String name, double amount, int id) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mCategoryDAO.updateCategory(name, amount, id);
        });
    }

    public LiveData<Category> getCategoryFrom(int catID){

        return this.mCategoryDAO.getCategoryFrom(catID);
    }

    /*
        SUBCATEGORY
     */

    public LiveData<List<SubCategory>> getAllSubCategories(){
        return mAllSubCategory;
    }
    public LiveData<List<SubCategory>> getSubCategoriesOf(int categoryID){
        return this.mSubCategoryDAO.getSubCategoriesOf(categoryID);
    }
    public void insert(SubCategory subCategory) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mSubCategoryDAO.insertSubCategory(subCategory);
        });
    }
    public void delete(SubCategory subCategory){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mSubCategoryDAO.deleteSubCategory(subCategory);
        });
    }
    public void update(int catID, String subcatName, int id){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mSubCategoryDAO.updateSubCategory(catID, subcatName, id);
        });
    }


    /*
        INOUTCOME
     */

    public LiveData<List<InOutCome>> getmAllInOutCome(){
        return mAllInOutCome;
    }
    public void insert(InOutCome inOutCome){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mInOutComeDAO.insertInOutCome(inOutCome);
        });
    }


    public LiveData<List<InOutCome>> getInOutComeOf(int subCategoryID) {
        return this.mInOutComeDAO.getInOutComesOf(subCategoryID);
    }

    public void delete(InOutCome inOutCome) {
        this.mInOutComeDAO.deleteInOutCome(inOutCome);
    }

    public void update(int catID, int subCatID, String name, LocalDate date, double amount, int id) {
        this.mInOutComeDAO.updateInOutCome(catID, subCatID, name, date, amount, id);
    }

    public LiveData<Integer> getPercentUsedOf(int subCategoryID){
        return this.mInOutComeDAO.getPercentUsedOf(subCategoryID);
    }

    public LiveData<Double> getBudgetLeftOf(int categoryID){
        return this.mInOutComeDAO.getBudgetLeftOf(categoryID);
    }

    public LiveData<Integer> getPercentUsedOfCategory(int categoryID){
        return this.mInOutComeDAO.getPercentUsedOfCategory(categoryID);
    }

    public LiveData<List<Category>> getCategoriesOfType(Category.CategoryType type){
        return this.mCategoryDAO.getCategoriesOfType(type);
    }

    public void update(Category category) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mCategoryDAO.updateCategory(category);
        });
    }
}
