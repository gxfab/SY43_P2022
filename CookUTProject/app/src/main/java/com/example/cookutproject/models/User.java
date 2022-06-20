package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;
    String nom;
    String prenom;

    /**
     * Constructeur de la classe user
     * @param nom
     * @param prenom
     */
    public User(String nom,String prenom){
        this.nom= nom;
        this.prenom=prenom;
    }

    /**
     * Récupération de l'id de l'utilisateur
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Récupération du nom de l'utilisateur
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupération du prénom de l'utilisateur
     * @return prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter de l'id de l'utilisateur
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter du nom de l'utilisateur
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter du prénom de l'utilisateur
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
