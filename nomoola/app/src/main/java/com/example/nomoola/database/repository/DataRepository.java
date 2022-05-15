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

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategory;
    }
    public void insert(Category Category) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mCategoryDAO.insertCategory(Category);
        });
    }

    public LiveData<List<SubCategory>> getAllSubCategories(){
        return mAllSubCategory;
    }
    public void insert(SubCategory subCategory) {
        NomoolaRoomDataBase.databaseWriteExecutor.execute(() -> {
            mSubCategoryDAO.insertSubCategory(subCategory);
        });
    }

    public LiveData<List<InOutCome>> getmAllInOutCome(){
        return mAllInOutCome;
    }
    public void insert(InOutCome inOutCome){
        NomoolaRoomDataBase.databaseWriteExecutor.execute(()->{
            mInOutComeDAO.insertInOutCome(inOutCome);
        });
    }
}
