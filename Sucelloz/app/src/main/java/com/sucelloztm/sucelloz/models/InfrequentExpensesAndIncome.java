package com.sucelloztm.sucelloz.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Infrequent expenses and income entity for the dao
 */
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
    private String sign;

    @ColumnInfo(name="date")
    private String date;

    @ColumnInfo(name="sub_categories_id")
    private long subCategoriesId;

    /**
     * default constructor
     */
    //CONSTRUCTOR
    public InfrequentExpensesAndIncome(){}

    /**
     * custom constructor
     * @param name name
     * @param amount amount
     * @param sign sign, + or -
     * @param date date
     * @param subCategoriesId id of the associated subcategory
     */
    public InfrequentExpensesAndIncome(String name, int amount, String sign, String date, long subCategoriesId){
        this.name=name;
        this.amount=amount;
        this.sign=sign;
        this.date=date;
        this.subCategoriesId=subCategoriesId;
    }

    /**
     * getter
     * @return id
     */
    public long getId(){
        return id;
    }

    /**
     * getter
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * getter
     * @return amount
     */
    public int getAmount(){
        return amount;
    }

    /**
     * getter
     * @return sign
     */
    public String getSign(){
        return sign;
    }

    /**
     * getter
     * @return date
     */
    public String getDate(){
        return date;
    }

    /**
     * getter
     * @return subcategory id
     */
    public long getSubCategoriesId(){
        return subCategoriesId;
    }

    /**
     * setter
     * @param id id
     */
    public void setId(long id){
        this.id=id;
    }

    /**
     * setter
     * @param name name
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * setter
     * @param amount amount
     */
    public void setAmount(int amount){
        this.amount=amount;
    }

    /**
     * setter
     * @param sign sign
     */
    public void setSign(String sign){
        this.sign=sign;
    }

    /**
     * setter
     * @param date date
     */
    public void setDate(String date){
        this.date=date;
    }

    /**
     * setter
     * @param subCategoriesId subcategory id
     */
    public void setSubCategoriesId(long subCategoriesId){
        this.subCategoriesId=subCategoriesId;
    }
}
