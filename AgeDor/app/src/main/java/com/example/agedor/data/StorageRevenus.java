package com.example.agedor.data;

public class StorageRevenus {
    public String date_depense;
    public String nom;
    public Double montant;
    public int id;

    // Constructeur pour notre classe
    public StorageRevenus(String date_depense, String nom, Double montant) {
        this.date_depense = date_depense;
        this.nom = nom;
        this.montant = montant;
    }
}