package com.example.cookutproject.models;

public class Operation {

    private Facture facture;
    private NoteDeFrais noteDeFrais;
    private int id;

    Operation(Facture facture, NoteDeFrais noteDeFrais, int id){
        this.facture = facture;
        this.noteDeFrais = noteDeFrais;
        this.id = id;
    }

    Operation(Facture facture){
        this.facture = facture;
        this.id = id;
    }

    public Facture getFacture() {
        return facture;
    }

    public NoteDeFrais getNoteDeFrais() {
        return noteDeFrais;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public void setNoteDeFrais(NoteDeFrais noteDeFrais) {
        this.noteDeFrais = noteDeFrais;
    }

    public int getId() {
        return id;
    }
}
