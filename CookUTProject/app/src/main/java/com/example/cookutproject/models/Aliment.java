package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Facture.class,

        parentColumns = "id",
        childColumns = "idFacture"))

public class Aliment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private float price;
    private int idFacture;
    private String name;
    private String type;
    private int quantity;

    /**
     * Création d'un aliment
     * @param id
     * @param price
     * @param idFacture
     * @param name
     * @param type
     * @param quantity
     */
    public Aliment (int id,float price,int idFacture, String name, String type, int quantity){
        this.id=id;
        this.price = price;
        this.idFacture=idFacture;
        this.name=name;
        this.type=type;
        this.quantity=quantity;
    }

    //GETTER

    /**
     *
     * @return price
     */
    public float getPrice() {
        return price;
    }

    /**
     *
     * @return idFacture
     */
    public int getIdFacture(){
        return idFacture;
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    //SETTER

    /**
     *
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     *
     * @param idFacture
     */
    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
