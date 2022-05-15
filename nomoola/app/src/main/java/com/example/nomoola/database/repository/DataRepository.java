package com.example.nomoola.database.repository;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.nomoola.database.dao.CategoryDAO;
import com.example.nomoola.database.dao.UnderCategoryDAO;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.UnderCategory;
import com.example.nomoola.database.roomDataBase.NomoolaRoomDataBase;
import java.util.List;

public class DataRepository {

    private CategoryDAO mCategoryDAO;
    private LiveData<List<Category>> mAllCategory;

    private UnderCategoryDAO mUnderCategory;
    private LiveData<List<UnderCategory>> mAllUnderCategory;

    public DataRepository(Application application) {
        Log.d("CREATION", "Instantiation of CategoryRepository");
        NomoolaRoomDataBase db = NomoolaRoomDataBase.getDatabase(application);

        mCategoryDAO = db.categoryDAO();
        mAllCategory = mCategoryDAO.getAllCategories();

        mUnderCategory = db.underCategoryDAO();
        mAllUnderCategory = mUnderCategory.getAllUnderCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategory;
    }
    public void insert(Category Category) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mCategoryDAO.insertCategory(Category);
        });
    }

    public LiveData<List<UnderCategory>> getAllUnderCategories(){return mAllUnderCategory;}
    public void insert(UnderCategory underCategory) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mUnderCategory.insertUnderCategory(underCategory);
        });
    }

}
