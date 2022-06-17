package com.example.cookutproject.data;

import androidx.lifecycle.LiveData;

import com.example.cookutproject.models.Semestre;

import java.util.List;

public class CookUTRepository {

    private CookUTDao cookUTDao;
    private LiveData<List<Semestre>> mAllSemestre;

    public CookUTRepository(CookUTDao cookUTDao){
        mAllSemestre = cookUTDao.readAllSemestre();
        this.cookUTDao=cookUTDao;
    }

    public void addSemestre(Semestre s){
        CookUTDatabase.databasWriteExecutor.execute(()->{
            cookUTDao.addSemestre(s);
        });
    }

    public LiveData<List<Semestre>> getmAllSemestre() {
        return mAllSemestre;
    }
}
