package com.example.sy43.models;

public class Category {
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
    private int CatID;
    public boolean isObjective;
    private String CatName;

    private float MaxValue;

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
