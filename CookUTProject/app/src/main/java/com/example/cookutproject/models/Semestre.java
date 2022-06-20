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

    /**
     * Création d'un semestre via son constructeur
     * @param nom
     * @param montantPrev
     * @param montantDep
     * @param montantRec
     */
    public Semestre(String nom,float montantPrev,float montantDep,float montantRec){
        this.montantDep = montantDep;
        this.montantPrev =montantPrev;
        this.montantRec = montantRec;
        this.nom =nom;
    }

    //GETTER

    /**
     * Récupération de l'id du semestre
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Récupération du nom du semestre
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupération du montant des dépenses
     * @return montantDep
     */
    public float getMontantDep() {
        return montantDep;
    }

    /**
     * Récupération du montant prévisionnel
     * @return montantPrev
     */
    public float getMontantPrev() {
        return montantPrev;
    }

    /**
     * Récupération du montant des recettes
     * @return montantRec
     */
    public float getMontantRec() {
        return montantRec;
    }

    //SETTER

    /**
     * Setter du nom du semestre
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *Setter de l'ID du semestre
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *Setter du montant des dépenses du semestre
     * @param montantDep
     */
    public void setMontantDep(float montantDep) {
        this.montantDep = montantDep;
    }

    /**
     *Setter du montant prévisionnel du semestre
     * @param montantPrev
     */
    public void setMontantPrev(float montantPrev) {
        this.montantPrev = montantPrev;
    }

    /**
     *Setter du montant des recettes du semestre
     * @param montantRec
     */
    public void setMontantRec(float montantRec) {
        this.montantRec = montantRec;
    }
}
