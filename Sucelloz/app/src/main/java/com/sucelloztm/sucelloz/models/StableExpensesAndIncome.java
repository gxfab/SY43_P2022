package com.sucelloztm.sucelloz.models;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.lang.String;

@Entity(tableName = "stable_expenses",
        foreignKeys = {@ForeignKey(entity = SubCategories.class,
        parentColumns = "id",
        childColumns = "sub_categories_id",
        onDelete = ForeignKey.CASCADE)
}
)
public class StableExpensesAndIncome {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="amount")
    private int amount;

    @ColumnInfo(name="sign")
    private char sign;

    @ColumnInfo(name="date")
    private int date;

    @ColumnInfo(name="frequency")
    private int frequency;

    @ColumnInfo(name="sub_categories_id")
    private long subCategoriesId;

    //CONSTRUCTOR
    public StableExpensesAndIncome(){}
    public StableExpensesAndIncome(String name, int amount, char sign,int date,int frequency, long subCategoriesId){
        this.name=name;
        this.amount=amount;
        this.sign=sign;
        this.date=date;
        this.frequency=frequency;
        this.subCategoriesId=subCategoriesId;
    }

    //GETTER
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getAmount(){
        return amount;
    }
    public char getSign(){
        return sign;
    }
    public int getDate(){
        return date;
    }
    public int getFrequency(){
        return frequency;
    }
    public long getSubCategoriesId(){
        return subCategoriesId;
    }

    //SETTER
    public void setId(long id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }
    public void setSign(char sign){
        this.sign=sign;
    }
    public void setDate(int date){
        this.date=date;
    }
    public void setFrequency(int frequency){
        this.frequency=frequency;
    }
    public void setSubCategoriesId(long subCategoriesId){
        this.subCategoriesId=subCategoriesId;
    }
}
