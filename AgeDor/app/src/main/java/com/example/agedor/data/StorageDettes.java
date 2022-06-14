package com.example.agedor.data;

public class StorageDettes {
    public String date_dette;
    public String nom;
    public Double montant;
    public int id;

    // Constructeur pour notre classe
    public StorageDettes(String date_dette, String nom, Double montant) {
        this.date_dette = date_dette;
        this.nom = nom;
        this.montant = montant;
    }
}
