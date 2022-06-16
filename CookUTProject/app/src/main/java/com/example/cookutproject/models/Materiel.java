package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(foreignKeys = @ForeignKey(entity = Facture.class,

        parentColumns = "id",

        childColumns = "id_facture"))
public class Materiel extends Budget{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int id_Facture;
    Materiel(float previsionnel, float depense, float recette, ArrayList<Operation> operation, int id, int id_Facture) {
        super(previsionnel, depense, recette, operation);
        this.id=id;
        this.id_Facture=id;
    }

    //GETTER
    public int getId() {
        return id;
    }

    public int getId_Facture() {
        return id_Facture;
    }

    //SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setId_Facture(int id_Facture) {
        this.id_Facture = id_Facture;
    }
}
