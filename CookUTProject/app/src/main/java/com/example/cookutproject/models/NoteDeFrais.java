package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Evenement.class,
                parentColumns = "id",
                childColumns = "id_evenement"),
        @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "id_user")}
)
public class NoteDeFrais {
    
    private String nomPersonne;
    private String prenomPersonne;
    private float amount;
    private String title;
    private String date;
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_evenement;
    private int id_user;

    /**
     * Création d'une note de frais
     * @param nomPersonne
     * @param prenomPersonne
     * @param amount
     * @param title
     * @param date
     * @param id
     * @param id_evenement
     * @param id_user
     */
    public NoteDeFrais(String nomPersonne,String prenomPersonne,int amount, String title, String date, int id, int id_evenement, int id_user){
        this.nomPersonne = nomPersonne;
        this.prenomPersonne = prenomPersonne;
        this.amount = amount;
        this.title=title;
        this.date=date;
        this.id=id;
        this.id_evenement=id_evenement;
        this.id_user=id_user;
    }

    //GETTER

    /**
     * Récupération du nom de la personne associée à la note de frais
     * @return nomPersonne
     */
    public String getNomPersonne() {
        return nomPersonne;
    }

    /**
     * Récupération du prénom de la personne associée à la note de frais
     * @return prenomPersonne
     */
    public String getPrenomPersonne() {
        return prenomPersonne;
    }

    /**
     * Récupération du montant de la note de frais
     * @return amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Récupération du titre de la note de frais
     * @return title
     */
    public String getTitle(){
        return title;
    }

    /**
     * Récupération de l'id de la note de frais
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Récupération de la date associée à la note de frais
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Récupération l'id de l'évènement associée à la note de frais
     * @return id_evenement
     */
    public int getId_evenement() {
        return id_evenement;
    }

    /**
     * Récupération de l'id de la personne associée à la note de frais, idéalement nous voulions utiliser la table/class user de  la BDD
     * pour pouvoir avoir toutes les informations concernant l'utilisateur lié à la note de frais
     * @return id_user
     */
    public int getId_user() {
        return id_user;
    }

    //SETTER

    /**
     * Setter de nom de la personne associée à la note de frais
     * @param nomPersonne
     */
    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    /**
     * Setter de prénom de la personne associée à la note de frais
     * @param prenomPersonne
     */
    public void setPrenomPersonne(String prenomPersonne) {
        this.prenomPersonne = prenomPersonne;
    }

    /**
     * Setter du montant
     * @param amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Setter de la date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Setter de l'id de la note de frais
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter de l'id de l'évènement associé à la note de frais
     * @param id_evenement
     */
    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    /**
     * Setter du titre de la note de frais
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter de l'id de l'utilisateur associé à la note de frais
     * @param id_user
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
