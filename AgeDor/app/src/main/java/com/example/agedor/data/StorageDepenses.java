package com.example.agedor.data;

public class StorageDepenses {
    public int id_cat;
    public String date_depense;
    public String nom;
    public Double montant;
    public int id;

    // Constructeur pour notre classe
    public StorageDepenses(int id_cat, String date_depense, String nom, Double montant) {
        this.id_cat = id_cat;
        this.date_depense = date_depense;
        this.nom = nom;
        this.montant = montant;
    }
}

