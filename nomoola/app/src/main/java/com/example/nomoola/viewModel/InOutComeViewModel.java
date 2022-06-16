package com.example.nomoola.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.nomoola.database.entity.Category;
import com.example.nomoola.database.entity.InOutCome;
import com.example.nomoola.database.entity.SubCategory;
import com.example.nomoola.database.repository.DataRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InOutComeViewModel extends AndroidViewModel {

    private final DataRepository mRepository;
    private LiveData<List<InOutCome>> mInOutComes;

    public InOutComeViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new DataRepository(application);
        this.mInOutComes = mRepository.getmAllInOutCome();
    }

    public LiveData<List<InOutCome>> getmInOutComes(){
        return this.mInOutComes;
    }

    public LiveData<List<InOutCome>> getInOutComeOf(int subCategoryID){
        return this.mRepository.getInOutComeOf(subCategoryID);
    }

    public void insert(InOutCome inOutCome){
        this.mRepository.insert(inOutCome);
    }

    public void delete(InOutCome inOutCome){
        this.mRepository.delete(inOutCome);
    }

    public void update(int catID, int subCatID, String name, LocalDate date, double amount, int id){
        mRepository.update(catID, subCatID, name, date, amount, id);
    }

    public void update(InOutCome inOutCome){
        mRepository.update(inOutCome);
    }

    public LiveData<List<String>> getAllSubCategoriesNames(){
        return this.mRepository.getAllSubCategoriesNames();
    }

    public SubCategory getSubCategoriesNamed(String name){
        return this.mRepository.getSubCategoriesNamed(name);
    }
}
