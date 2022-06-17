package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Evenement {
    @PrimaryKey(autoGenerate = true)
    int id;
    String type;
    String name;
    Date date;
    float amountPrevisionnel;
    float amountExpense;
    float amountRecipe;

    //Constructor
    public Evenement(int id,String type, String name, Date date, float amountPrevisionnel, float amountExpense, float amountRecipe){
        this.id=id;
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

    public Date getDate(){
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

    //SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
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

}
