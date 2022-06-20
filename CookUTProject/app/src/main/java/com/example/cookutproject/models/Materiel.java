package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(foreignKeys = @ForeignKey(entity = Facture.class,

        parentColumns = "id",

        childColumns = "idFacture"))
public class Materiel {
    @PrimaryKey(autoGenerate = true)
    int id;
    float price;
    int idFacture;
    String name;
    String type;
    int quantity;

    /**
     * Création d'un matériel de cuisine
     * @param id
     * @param price
     * @param idFacture
     * @param name
     * @param type
     * @param quantity
     */
    public Materiel (int id,float price,int idFacture, String name, String type, int quantity){
        this.id=id;
        this.price = price;
        this.idFacture=idFacture;
        this.name=name;
        this.type=type;
        this.quantity=quantity;
    }

    //GETTER

    /**
     * Récupération du prix du matériel
     * @return price
     */
    public float getPrice() {
        return price;
    }

    /**
     * récupération de l'id de la facture qui lui est associé
     * @return
     */
    public int getIdFacture(){
        return idFacture;
    }

    /**
     * récupération de l'id du matériel
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * récupération de la quantité achetée
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * récupération du nom
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * récupération du type de matériel
     * @return
     */
    public String getType() {
        return type;
    }

    //SETTER

    /**
     * Setter du prix
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Setter de l'id de la facture dont le matériel est associé
     * @param idFacture
     */
    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    /**
     * Setter du type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Setter du nom
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter de l'id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter de la quantité
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
