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
    private Date date;
    private int id_event;
    private String entity;
    private String title;

    //Constructor
    public Facture (float amount, Date date, int id, String entity, String title,int id_event){
        this.amount = amount;
        this.date = date;
        this.id =id;
        this.entity=entity;
        this.title=title;
        this.id_event=id_event;
    }

    //GETTER

    public float getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public int getId(){
        return id;
    }

    public int getId_event(){
        return id_event;
    }

    public String getEntity(){
        return entity;
    }

    public String getTitle(){
        return title;
    }


    //SETTER

    public void setAmount(float amount){
        this.amount = amount;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setId_facture(int idFacture){
        this.id = idFacture;
    }

    public void setId_event(int id_event){
        this.id_event = id_event;
    }

    public void setEntity(String entity){
        this.entity = entity;
    }

    public void setTitle(String title){
        this.title = title;
    }

}