package com.sucelloztm.sucelloz.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "infrequent_expenses",
        foreignKeys = {@ForeignKey(entity = SubCategories.class,
        parentColumns = "id",
        childColumns = "sub_categories_id",
        onDelete = ForeignKey.CASCADE)
})
public class InfrequentExpensesAndIncome {
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

    @ColumnInfo(name="sub_categories_id")
    private long subCategoriesId;

    //CONSTRUCTOR
    public InfrequentExpensesAndIncome(){}
    public InfrequentExpensesAndIncome(String name, int amount, char sign,int date, long subCategoriesId){
        this.name=name;
        this.amount=amount;
        this.sign=sign;
        this.date=date;
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
    public void setSubCategoriesId(long subCategoriesId){
        this.subCategoriesId=subCategoriesId;
    }
}
