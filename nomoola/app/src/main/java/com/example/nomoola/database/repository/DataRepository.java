package com.example.nomoola.database.repository;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.nomoola.database.dao.CategoryDAO;
import com.example.nomoola.database.dao.SubCategoryDAO;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.database.roomDataBase.NomoolaRoomDataBase;
import java.util.List;

public class DataRepository {

    private CategoryDAO mCategoryDAO;
    private LiveData<List<Category>> mAllCategory;

    private SubCategoryDAO mSubCategory;
    private LiveData<List<SubCategory>> mAllSubCategory;

    public DataRepository(Application application) {
        Log.d("CREATION", "Instantiation of CategoryRepository");
        NomoolaRoomDataBase db = NomoolaRoomDataBase.getDatabase(application);

        mCategoryDAO = db.categoryDAO();
        mAllCategory = mCategoryDAO.getAllCategories();

        mSubCategory = db.subCategoryDAO();
        mAllSubCategory = mSubCategory.getAllSubCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategory;
    }
    public void insert(Category Category) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mCategoryDAO.insertCategory(Category);
        });
    }

    public LiveData<List<SubCategory>> getAllUnderCategories(){return mAllSubCategory;}
    public void insert(SubCategory subCategory) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mSubCategory.insertSubCategory(subCategory);
        });
    }

}
