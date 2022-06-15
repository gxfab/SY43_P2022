package com.example.cookutproject;

public class NoteDeFrais {

    private String nomPersonne;
    private String prenomPersonne;
    private float amount;

    NoteDeFrais(String nomPersonne,String prenomPersonne,int amount){
        this.nomPersonne = nomPersonne;
        this.prenomPersonne = prenomPersonne;
        this.amount = amount;
    }
    public String getNomPersonne() {
        return nomPersonne;
    }

    public String getPrenomPersonne() {
        return prenomPersonne;
    }

    public float getAmount() {
        return amount;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public void setPrenomPersonne(String prenomPersonne) {
        this.prenomPersonne = prenomPersonne;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
