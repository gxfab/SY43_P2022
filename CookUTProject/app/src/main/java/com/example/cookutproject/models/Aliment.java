package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Facture.class,

        parentColumns = "id",

        childColumns = "id_facture"))
public class Aliment {
    @PrimaryKey(autoGenerate = true)
    int id;
    float price;
    int id_facture;
    String name;
    String type;
    int quantity;

    public Aliment (int id,int price,int id_facture, String name, String type, int quantity){
        this.id=id;
        this.price = price;
        this.id_facture=id_facture;
        this.name=name;
        this.type=type;
        this.quantity=quantity;
    }

    //GETTER
    public float getPrice() {
        return price;
    }

    public int getId_facture(){
        return id_facture;
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

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
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
