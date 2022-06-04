package com.example.nomoola.database.entity;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "T_CATEGORY")
public class Category {

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

    /**
     * CONSTRUCTOR
     */

    public Category(){
    }

    public Category(@NonNull String name, double budgetAmount){
        Log.d("CREATION", "Instantiation of Category = " + name);
        this.m_CAT_NAME = name;
        this.m_CAT_BUDGET_AMOUNT = budgetAmount;
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
}
