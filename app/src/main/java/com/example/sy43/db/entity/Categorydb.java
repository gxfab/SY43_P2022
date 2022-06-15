package com.example.sy43.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Categorydb", indices =
{@Index(value = {"CatName"},unique=true)})

public class Categorydb {
    @PrimaryKey(autoGenerate = true)
    private int CatID;

    @ColumnInfo( name = "CatName")
    private String CatName;

    @ColumnInfo( name = "MaxValue")
    private float MaxValue;

    @ColumnInfo( name = "CurrentValue")
    private float CurrentValue;

    @ColumnInfo( name = "isObjective")
    private boolean isObjective;

    @ColumnInfo( name = "date")
    private long date;

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

    public boolean getIsObjective() {
        return isObjective;
    }

    public long getDate() {
        return date;
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

    public void setObjective(boolean objective) {
        isObjective = objective;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
