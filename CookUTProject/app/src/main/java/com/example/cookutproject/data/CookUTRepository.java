package com.example.cookutproject.data;

import androidx.lifecycle.LiveData;

import com.example.cookutproject.models.Evenement;
import com.example.cookutproject.models.Semestre;

import java.util.List;

public class CookUTRepository {

    private CookUTDao cookUTDao;
    private LiveData<List<Semestre>> mAllSemestre;
    private LiveData<List<Evenement>> mAllEvenement;

    /**
     * Constructeur du Repository permettant l'accès à la BDD en arrière plan
     * @param cookUTDao
     */
    public CookUTRepository(CookUTDao cookUTDao){
        mAllSemestre = cookUTDao.readAllSemestre();
        mAllEvenement = cookUTDao.readAllEvenement();
        this.cookUTDao=cookUTDao;
    }

    /**
     * Ajoute un semestre dans la BDD
     * @param s
     */
    public void addSemestre(Semestre s){
        CookUTDatabase.databasWriteExecutor.execute(()->{
            cookUTDao.addSemestre(s);
        });
    }

    /**
     * Ajoute un évènement dans la BDD
     * @param e
     */
    public void addEvenement(Evenement e) {
        CookUTDatabase.databasWriteExecutor.execute(() -> {
            cookUTDao.addEvenement(e);
        });
    }

    /**
     * Met à jour les semestres
     * @return
     */
    public LiveData<List<Semestre>> getmAllSemestre() {
        return mAllSemestre;
    }

    /**
     * Met à jour les évènements
     * @return
     */
    public LiveData<List<Evenement>> getmAllEvenement() {
        return mAllEvenement;
    }
}
