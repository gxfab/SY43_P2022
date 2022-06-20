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

    /**
     * Création d'un évènement
     * @param idSemestre
     * @param type
     * @param name
     * @param date
     * @param amountPrevisionnel
     * @param amountExpense
     * @param amountRecipe
     */
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

    /**
     * Récupération id
     * @return id
     */
    public int getId(){
        return id;
    }

    /**
     *
     * @return
     */
    public String getType(){
        return type;
    }

    /**
     *
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return
     */
    public String getDate(){
        return date;
    }

    /**
     *
     * @return
     */
    public float getAmountExpense() {
        return amountExpense;
    }

    /**
     *
     * @return
     */
    public float getAmountPrevisionnel() {
        return amountPrevisionnel;
    }

    /**
     *
     * @return
     */
    public float getAmountRecipe() {
        return amountRecipe;
    }

    /**
     *
     * @return
     */
    public int getIdSemestre() {
        return idSemestre;
    }

    //SETTER

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @param amountExpense
     */
    public void setAmountExpense(float amountExpense) {
        this.amountExpense = amountExpense;
    }

    /**
     *
     * @param amountPrevisionnel
     */
    public void setAmountPrevisionnel(float amountPrevisionnel) {
        this.amountPrevisionnel = amountPrevisionnel;
    }

    /**
     *
     * @param amountRecipe
     */
    public void setAmountRecipe(float amountRecipe) {
        this.amountRecipe = amountRecipe;
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
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @param idSemestre
     */
    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }
}
