package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Semestre.class,
        parentColumns = "id",
        childColumns = "idSemestre")
})
public class Evenement {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String type;
    private String name;
    private String date;
    private int idSemestre;
    private float amountPrevisionnel;
    private float amountExpense;
    private float amountRecipe;

    //Constructor
    public Evenement(int idSemestre,String type, String name, String date, float amountPrevisionnel, float amountExpense, float amountRecipe){
        this.idSemestre=idSemestre;
        this.type=type;
        this.name=name;
        this.date=date;
        this.amountPrevisionnel=amountPrevisionnel;
        this.amountExpense=amountExpense;
        this.amountRecipe=amountRecipe;
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

    public String getDate(){
        return date;
    }

    public float getAmountExpense() {
        return amountExpense;
    }

    public float getAmountPrevisionnel() {
        return amountPrevisionnel;
    }

    public float getAmountRecipe() {
        return amountRecipe;
    }

    public int getIdSemestre() {
        return idSemestre;
    }

    //SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmountExpense(float amountExpense) {
        this.amountExpense = amountExpense;
    }

    public void setAmountPrevisionnel(float amountPrevisionnel) {
        this.amountPrevisionnel = amountPrevisionnel;
    }

    public void setAmountRecipe(float amountRecipe) {
        this.amountRecipe = amountRecipe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }
}
