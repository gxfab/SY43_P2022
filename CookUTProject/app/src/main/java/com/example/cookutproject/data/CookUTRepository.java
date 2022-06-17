package com.example.cookutproject.data;

import androidx.lifecycle.LiveData;

import com.example.cookutproject.models.Evenement;
import com.example.cookutproject.models.Semestre;

import java.util.List;

public class CookUTRepository {

    private CookUTDao cookUTDao;
    private LiveData<List<Semestre>> mAllSemestre;
    private LiveData<List<Evenement>> mAllEvenement;

    public CookUTRepository(CookUTDao cookUTDao){
        mAllSemestre = cookUTDao.readAllSemestre();
        mAllEvenement = cookUTDao.readAllEvenement();
        this.cookUTDao=cookUTDao;
    }

    public void addSemestre(Semestre s){
        CookUTDatabase.databasWriteExecutor.execute(()->{
            cookUTDao.addSemestre(s);
        });
    }

    public void addEvenement(Evenement e) {
        CookUTDatabase.databasWriteExecutor.execute(() -> {
            cookUTDao.addEvenement(e);
        });
    }

    public LiveData<List<Semestre>> getmAllSemestre() {
        return mAllSemestre;
    }

    public LiveData<List<Evenement>> getmAllEvenement() {
        return mAllEvenement;
    }
}
