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
    public String getNomPersonne() {
        return nomPersonne;
    }

    public String getPrenomPersonne() {
        return prenomPersonne;
    }

    public float getAmount() {
        return amount;
    }

    public String getTitle(){
        return title;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public int getId_user() {
        return id_user;
    }

    //SETTER

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public void setPrenomPersonne(String prenomPersonne) {
        this.prenomPersonne = prenomPersonne;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
