package com.example.sy43.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

/**
 * Structure d'une catégorie de dépense
 */
public class Category {
    /**
     *
     * @param name nom de la catégorie
     * @param currentValue dépense actuelle au niveau de la catégorie
     * @param maxValue montant maximal à associer à la catégorie
     */
    public Category(String name, float currentValue, float maxValue) {
        this.setCatName(name);
        this.setCurrentValue(currentValue);
        this.setMaxValue(maxValue);
        this.isObjective = false;
    }

    public Category(String name, float currentValue, float maxValue, boolean isObjective) {
        this.setCatName(name);
        this.setCurrentValue(currentValue);
        this.setMaxValue(maxValue);
        this.isObjective = isObjective;
    }
    @PrimaryKey(autoGenerate = true)
    private int CatID;

    @ColumnInfo( name = "isObjective")
    public boolean isObjective;

    @ColumnInfo( name = "CatName")
    private String CatName;

    @ColumnInfo( name = "MaxValue")
    private float MaxValue;

    @ColumnInfo( name = "CurrentValue")
    private float CurrentValue;

    public int getCatID(){
        return CatID;
    }

    public String getCatName(){
        return CatName;
    }

    public float getMaxValue(){
        return MaxValue;
    }

    public float CurrentValue(){
        return CurrentValue;
    }

    public void setCatID(int CatID){
        this.CatID=CatID;
    }

    public void setCatName(String CatName){
        this.CatName = CatName;
    }

    public void setMaxValue(float MaxValue){
        this.MaxValue=MaxValue;
    }

    public void setCurrentValue(float CurrentValue){
        this.CurrentValue=CurrentValue;
    }

    public String toString() {
        return this.getCatName() + ", $" + this.CurrentValue() + "/$" + this.getMaxValue();
    }
}
