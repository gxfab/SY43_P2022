package com.example.cookutproject.models;

public class Operation {

    private Facture facture;
    private NoteDeFrais noteDeFrais;
    private int id;

    /**
     * Création d'une opération associant une facture elle même associée à une note de frais
     * @param facture
     * @param noteDeFrais
     * @param id
     */
    Operation(Facture facture, NoteDeFrais noteDeFrais, int id){
        this.facture = facture;
        this.noteDeFrais = noteDeFrais;
        this.id = id;
    }

    /**
     * Création d'une opération uniquement composée d'une facture
     * @param facture
     */
    Operation(Facture facture){
        this.facture = facture;
        this.id = id;
    }

    //GETTER

    /**
     * Récupération de la facture
     * @return facture
     */
    public Facture getFacture() {
        return facture;
    }

    /**
     * Récupération de la note de frais
     * @return noteDeFrais
     */
    public NoteDeFrais getNoteDeFrais() {
        return noteDeFrais;
    }

    /**
     * Récupération de l'id
     * @return id
     */
    public int getId() {
        return id;
    }

    //SETTER

    /**
     * Setter de facture
     * @param facture
     */
    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    /**
     * Setter de note de frais
     * @param noteDeFrais
     */
    public void setNoteDeFrais(NoteDeFrais noteDeFrais) {
        this.noteDeFrais = noteDeFrais;
    }

    /**
     * Setter de l'id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
