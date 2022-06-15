package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Evenement {
    @PrimaryKey
    int id;
    String type;
    String name;
    Date date;
    float amount_prev;
    float amount_depense;
    float amount_recette;

    //Constructor
    public Evenement(int id,String type, String name, Date date, float amount_prev, float amount_depense, float amount_recette){
        this.id=id;
        this.type=type;
        this.name=name;
        this.date=date;
        this.amount_prev=amount_prev;
        this.amount_depense=amount_depense;
        this.amount_recette=amount_recette;
    }

    //GETTER

    public int getId(){
        return id;
    }

    public String getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public Date getDate(){
        return date;
    }

    public float getAmount_depense() {
        return amount_depense;
    }

    public float getAmount_prev() {
        return amount_prev;
    }

    public float getAmount_recette() {
        return amount_recette;
    }
}
