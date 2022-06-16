package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Facture.class,

        parentColumns = "id",

        childColumns = "id_facture"))
public abstract class Aliment {
    @PrimaryKey(autoGenerate = true)
    int id;
    float price;
    int id_facture;

    public Aliment (int id,int price){
        this.id=id;
        this.price = price;
    }
    public float getPrice() {
        return price;
    }

    public int getId_facture(){
        return id_facture;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }
}
