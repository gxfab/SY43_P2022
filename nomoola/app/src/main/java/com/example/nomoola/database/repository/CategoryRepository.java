package com.example.nomoola.database.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import com.example.nomoola.database.dao.CategoryDAO;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.roomDataBase.CategoryRoomDataBase;
import java.util.List;

public class CategoryRepository {

    private CategoryDAO mCategoryDAO;
    private LiveData<List<Category>> mAllCategory;

    public CategoryRepository(Application application) {
        Log.d("CREATION", "Instantiation of CategoryRepository");
        CategoryRoomDataBase db = CategoryRoomDataBase.getDatabase(application);
        mCategoryDAO = db.categoryDAO();
        mAllCategory = mCategoryDAO.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategory;
    }

    public void insert(Category Category) {
        CategoryRoomDataBase.databaseWriteExecutor.execute(() -> {
            mCategoryDAO.insertCategory(Category);
        });
    }
}
