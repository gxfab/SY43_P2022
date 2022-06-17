package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(foreignKeys = @ForeignKey(entity = Facture.class,

        parentColumns = "id",

        childColumns = "id_facture"))
public class Materiel {
    @PrimaryKey(autoGenerate = true)
    int id;
    float price;
    int idFacture;
    String name;
    String type;
    int quantity;

    public Materiel (int id,float price,int idFacture, String name, String type, int quantity){
        this.id=id;
        this.price = price;
        this.idFacture=idFacture;
        this.name=name;
        this.type=type;
        this.quantity=quantity;
    }

    //GETTER
    public float getPrice() {
        return price;
    }

    public int getIdFacture(){
        return idFacture;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    //SETTER
    public void setPrice(float price) {
        this.price = price;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
