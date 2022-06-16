package com.example.nomoola.database.entity;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.loader.app.LoaderManager;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "T_CATEGORY")
public class Category {

    public enum CategoryType{
        INCOME, OUTCOME, PROJECT;

        @Override
        public String toString(){
            if(this == INCOME){
                return "INCOME";
            }else if(this == OUTCOME){
                return "OUTCOME";
            }else if(this == PROJECT){
                return "PROJECT";
            }else{
                return super.toString();
            }
        }

        public static List<CategoryType> getAllPossibilities(){
            ArrayList<CategoryType> list = new ArrayList<>();
            list.add(CategoryType.INCOME);
            list.add(CategoryType.OUTCOME);
            list.add(CategoryType.PROJECT);

            return list;
        }
    }

    /**
     * ATTRIBUTE
     */

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "CAT_ID")
    private int m_CAT_ID;

    @NonNull
    @ColumnInfo(name = "CAT_NAME")
    private String m_CAT_NAME;

    @ColumnInfo(name = "CAT_BUDGET_AMOUNT")
    private double m_CAT_BUDGET_AMOUNT;

    @ColumnInfo(name = "CAT_TYPE")
    private CategoryType m_CAT_TYPE;

    @ColumnInfo(name = "CAT_DATE")
    private LocalDate m_CAT_DATE;

    /**
     * CONSTRUCTOR
     */

    public Category(){
    }

    public Category(@NonNull String name, double budgetAmount, CategoryType type, LocalDate date){
        Log.d("CREATION", "Instantiation of Category = " + name);
        this.m_CAT_NAME = name;
        this.m_CAT_BUDGET_AMOUNT = budgetAmount;
        this.m_CAT_TYPE = type;
        this.m_CAT_DATE = date;
    }

    /**
     * GETTER / SETTER
     */
    @NonNull
    public String getM_CAT_NAME() {
        return m_CAT_NAME;
    }

    public void setM_CAT_NAME(@NonNull String m_CAT_NAME) {
        this.m_CAT_NAME = m_CAT_NAME;
    }

    public double getM_CAT_BUDGET_AMOUNT() {
        return m_CAT_BUDGET_AMOUNT;
    }

    public void setM_CAT_BUDGET_AMOUNT(double m_CAT_BUDGET_AMOUNT) {
        this.m_CAT_BUDGET_AMOUNT = m_CAT_BUDGET_AMOUNT;
    }

    public int getM_CAT_ID() {
        return m_CAT_ID;
    }

    public void setM_CAT_ID(int m_CAT_ID) {
        this.m_CAT_ID = m_CAT_ID;
    }

    public LocalDate getM_CAT_DATE() {
        return m_CAT_DATE;
    }

    public void setM_CAT_DATE(LocalDate m_CAT_DATE) {
        this.m_CAT_DATE = m_CAT_DATE;
    }

    public CategoryType getM_CAT_TYPE() {
        return m_CAT_TYPE;
    }

    public void setM_CAT_TYPE(CategoryType m_CAT_TYPE) {
        this.m_CAT_TYPE = m_CAT_TYPE;
    }

}
