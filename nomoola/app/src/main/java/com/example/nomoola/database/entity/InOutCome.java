package com.example.nomoola.database.entity;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "T_INOUTCOME")
public class InOutCome {

//    public enum InOutComeType{
//        INCOME, OUTCOME;
//
//        @Override
//        public String toString(){
//            if(this == INCOME){
//                return "INCOME";
//            }else if(this == OUTCOME){
//                return "OUTCOME";
//            }else{
//                return super.toString();
//            }
//        }
//
//        public static List<Category.CategoryType> getAllPossibilities(){
//            ArrayList<Category.CategoryType> list = new ArrayList<>();
//            list.add(Category.CategoryType.INCOME);
//            list.add(Category.CategoryType.OUTCOME);
//
//            return list;
//        }
//    }


    /**
     * ATTRIBUTE
     */
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "INOUTCOME_ID")
    private int m_INOUTCOME_ID;

    @NonNull
    @ColumnInfo(name = "INOUTCOME_NAME")
    private String m_INOUTCOME_NAME;

    @NonNull
    @ColumnInfo(name = "CAT_ID")
    private int m_CAT_ID;

    @NonNull
    @ColumnInfo(name = "SUBCAT_ID")
    private int m_SUBCAT_ID;

    @ColumnInfo(name = "INOUTCOME_AMOUNT")
    private double m_INOUTCOME_AMOUNT;

    @NonNull
    @ColumnInfo(name = "INOUTCOME_DATE")
    private LocalDate m_INOUTCOME_DATE;



    /**
     * Constructor
     */

    public InOutCome(){

    }

    public InOutCome(String inOutName, int catID, int subCatID, Double amount, LocalDate date){
        Log.d("CREATION", "Instantiation of Category = " + inOutName);
        this.m_INOUTCOME_NAME = inOutName;
        this.m_CAT_ID = catID;
        this.m_SUBCAT_ID = subCatID;
        this.m_INOUTCOME_AMOUNT = amount;
        this.m_INOUTCOME_DATE = date;
    }

    /**
     *  GETTER / SETTER
     */

    @NonNull
    public String getM_INOUTCOME_NAME() {
        return m_INOUTCOME_NAME;
    }

    public void setM_INOUTCOME_NAME(@NonNull String m_INOUTCOME_NAME) {
        this.m_INOUTCOME_NAME = m_INOUTCOME_NAME;
    }

    @NonNull
    public int getM_INOUTCOME_ID() {
        return m_INOUTCOME_ID;
    }

    public void setM_INOUTCOME_ID(@NonNull int m_INOUTCOME_ID) {
        this.m_INOUTCOME_ID = m_INOUTCOME_ID;
    }

    public int getM_CAT_ID() {
        return m_CAT_ID;
    }

    public void setM_CAT_ID(int m_CAT_ID) {
        this.m_CAT_ID = m_CAT_ID;
    }

    public int getM_SUBCAT_ID() {
        return m_SUBCAT_ID;
    }

    public void setM_SUBCAT_ID(int m_SUBCAT_ID) {
        this.m_SUBCAT_ID = m_SUBCAT_ID;
    }

    public double getM_INOUTCOME_AMOUNT() {
        return m_INOUTCOME_AMOUNT;
    }

    public void setM_INOUTCOME_AMOUNT(double m_INOUTCOME_AMOUNT) {
        this.m_INOUTCOME_AMOUNT = m_INOUTCOME_AMOUNT;
    }

    @NonNull
    public LocalDate getM_INOUTCOME_DATE() {
        return m_INOUTCOME_DATE;
    }

    public void setM_INOUTCOME_DATE(@NonNull LocalDate m_INOUTCOME_DATE) {
        this.m_INOUTCOME_DATE = m_INOUTCOME_DATE;
    }

}
