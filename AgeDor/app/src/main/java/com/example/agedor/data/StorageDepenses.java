package com.example.agedor.data;

public class StorageDepenses {
    public String categorie;
    public String date_depense;
    public String nom;
    public Double montant;
    public int id;

    // Constructeur pour notre classe
    public StorageDepenses(String categorie, String date_depense, String nom, Double montant) {
        this.categorie = categorie;
        this.date_depense = date_depense;
        this.nom = nom;
        this.montant = montant;
    }
}


