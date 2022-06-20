package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Evenement.class,

        parentColumns = "id",

        childColumns = "id_event"))
public class Facture {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private float amount;
    private String date;
    private int id_event;
    private String entity;
    private String title;

    //Constructor

    /**
     * Création d'une facture
     * @param amount
     * @param date
     * @param id
     * @param entity
     * @param title
     * @param id_event
     */
    public Facture (float amount, String date, int id, String entity, String title,int id_event){
        this.amount = amount;
        this.date = date;
        this.id =id;
        this.entity=entity;
        this.title=title;
        this.id_event=id_event;
    }

    //GETTER

    /**
     * récupération du montant
     * @return montant
     */
    public float getAmount() {
        return amount;
    }

    /**
     * récupération date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * récupération de l'id de la facture
     * @return id
     */
    public int getId(){
        return id;
    }

    /**
     * récupération de l'id de l'évènement associé à la facture
     * @return id de l'évènement
     */
    public int getId_event(){
        return id_event;
    }

    /**
     * récupération du nom de l'entité
     * @return l'entité qui paye/ est à l'origine de la facture
     */
    public String getEntity(){
        return entity;
    }

    /**
     * récupération du titre de la facture
     * @return title
     */
    public String getTitle(){
        return title;
    }


    //SETTER

    /**
     * Setter du montant
     * @param amount
     */
    public void setAmount(float amount){
        this.amount = amount;
    }

    /**
     * Setter de la date
     * @param date
     */
    public void setDate(String date){
        this.date = date;
    }

    /**
     * Setter de l'id de la facture
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Setter de l'id de l'évènement associé à la facture
     * @param id_event
     */
    public void setId_event(int id_event){
        this.id_event = id_event;
    }

    /**
     * Setter du nom de l'entité
     * @param entity
     */
    public void setEntity(String entity){
        this.entity = entity;
    }

    /**
     * Setter du titre de la facture
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }

}