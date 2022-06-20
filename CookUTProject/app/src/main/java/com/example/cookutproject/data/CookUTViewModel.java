package com.example.cookutproject.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cookutproject.models.Evenement;
import com.example.cookutproject.models.Semestre;

import java.util.List;

public class CookUTViewModel extends AndroidViewModel {

    private LiveData<List<Semestre>> readAllSemestre;
    private LiveData<List<Evenement>> readAllEvenement;
    private CookUTRepository repository;

    /**
     *
     * @param application
     */
    public CookUTViewModel(@NonNull Application application) {
        super(application);
        CookUTDao cookUTDao = CookUTDatabase.getDatabase(application).cookUTDao();
        repository = new CookUTRepository(cookUTDao);
        readAllSemestre = repository.getmAllSemestre();
        readAllEvenement = repository.getmAllEvenement();
    }

    public void addSemestre(Semestre s){
        repository.addSemestre(s);
    }

    public LiveData<List<Semestre>> getReadAllSemestre(){
        return readAllSemestre;
    }

    public void addEvenement(Evenement e){repository.addEvenement(e);}

    public LiveData<List<Evenement>> getReadAllEvenement() {
        return readAllEvenement;
    }
}
