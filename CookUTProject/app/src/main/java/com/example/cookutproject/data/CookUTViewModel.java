package com.example.cookutproject.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cookutproject.models.Semestre;

import java.util.List;

public class CookUTViewModel extends AndroidViewModel {

    private LiveData<List<Semestre>> readAllSemestre;
    private CookUTRepository repository;

    public CookUTViewModel(@NonNull Application application) {
        super(application);
        CookUTDao cookUTDao = CookUTDatabase.getDatabase(application).cookUTDao();
        repository = new CookUTRepository(cookUTDao);
        readAllSemestre = repository.getmAllSemestre();

    }

    public void addSemestre(Semestre s){
        repository.addSemestre(s);
    }

    public LiveData<List<Semestre>> getReadAllSemestre(){
        return readAllSemestre;
    }
}
