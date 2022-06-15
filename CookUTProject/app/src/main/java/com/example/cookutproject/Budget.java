package com.example.cookutproject;

import java.util.ArrayList;
import java.util.List;

public abstract class Budget {
    private float previsionnel;
    private float depense;
    private float recette;
    private List<Facture> factures = new ArrayList<Facture>() {
    };

    Budget(float previsionnel, float depense, float recette, ArrayList<Facture> facture){
        this.previsionnel = previsionnel;
        this.depense = depense;
        this.recette = recette;
        this.factures = facture;
    }
    public float getPrevisionnel() {
        return previsionnel;
    }

    public float getDepense() {
        return depense;
    }

    public float getRecette() {
        return recette;
    }

    public List<Facture> getAllFacture() {
        return factures;
    }

    public void setPrevisionnel(float previsionnel) {
        this.previsionnel = previsionnel;
    }

    public void setDepense(float depense) {
        this.depense += depense;
    }

    public void setRecette(float recette) {
        this.recette += recette;
    }

    public void setFacture(Facture facture) {
        this.factures.add(facture);
    }

    /**
     * Retourne une facture suivant le numéro de facture demandé
     * @param id l'identifiant de la facture
     * @return une facture ou null si la facture n'existe pas
     */

    public Facture getFactureById (int id){
        Facture factureToReturn;
        int i=0;
        Facture enumarationFacture=factures.get(i);
        while (enumarationFacture.getId()!= id){
            enumarationFacture = factures.get(i);
        }
        if(enumarationFacture.getId()==id){
            return enumarationFacture;
        }else
            return null;

    }
}
