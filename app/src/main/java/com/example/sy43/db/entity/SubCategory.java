package com.example.sy43.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


/**
 *Table les sous-catégories leurs valeurs maximal voulu, leurs valeurs actuel et la catégorie à laquelle elle appartient
 */
@Entity(tableName = "SubCategory",indices ={
@Index(value = {"SubCatID"}, unique = true)},
foreignKeys ={@ForeignKey(entity = Categorydb.class, parentColumns ="CatID", childColumns = "Category",onDelete = ForeignKey.CASCADE)})

public class SubCategory {
    @PrimaryKey(autoGenerate = true)
    private int SubCatID;
    
    @ColumnInfo( name = "SubCatName")
    private String SubCatName;

    @ColumnInfo( name = "MaxValue")
    private float MaxValue;

    @ColumnInfo( name = "CurrentValue")
    private float CurrentValue;

    @ColumnInfo (name = "Category")
    private int Category;

    public int getSubCatID(){
        return SubCatID;
    }

    public String getSubCatName(){
        return SubCatName;
    }

    public float getMaxValue(){
        return MaxValue;
    }

    public float CurrentValue(){
        return CurrentValue;
    }

    public int getCategory(){
        return Category;
    }

    public void setSubCatID(int SubCatID){
        this.SubCatID=SubCatID;
    }

    public void setSubCatName(String SubCatName){
        this.SubCatName = SubCatName;
    }

    public void setMaxValue(float MaxValue){
        this.MaxValue=MaxValue;
    }

    public void setCurrentValue(float CurrentValue){
        this.CurrentValue=CurrentValue;
    }

    public void setCategory(int Category){
        this.Category=Category;
    }
}
