package com.example.nomoola.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.repository.DataRepository;
import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private final DataRepository mRepository;
    private final LiveData<List<Category>> mAllCategories;


    public CategoryViewModel(@NonNull Application application) {
        super(application);
        Log.d("CREATION", "Instantiation of CategoryViewModel");
        mRepository = new DataRepository(application);
        mAllCategories = mRepository.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories(){
        return mAllCategories;
    }

    public void insert(Category category){
        mRepository.insert(category);
    }
}
