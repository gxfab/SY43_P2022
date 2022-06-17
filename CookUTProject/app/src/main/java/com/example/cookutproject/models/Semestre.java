package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Semestre {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private float montantPrev;
    private float montantDep;
    private float montantRec;

    public Semestre(String nom,float montantPrev,float montantDep,float montantRec){
        this.montantDep = montantDep;
        this.montantPrev =montantPrev;
        this.montantRec = montantRec;
        this.nom =nom;
    }

    //GETTER
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public float getMontantDep() {
        return montantDep;
    }

    public float getMontantPrev() {
        return montantPrev;
    }

    public float getMontantRec() {
        return montantRec;
    }

    //SETTER
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMontantDep(float montantDep) {
        this.montantDep = montantDep;
    }

    public void setMontantPrev(float montantPrev) {
        this.montantPrev = montantPrev;
    }

    public void setMontantRec(float montantRec) {
        this.montantRec = montantRec;
    }
}
