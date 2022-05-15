package com.example.nomoola.database.entity;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import java.time.LocalDate;

@Entity(tableName = "T_INOUTCOME", primaryKeys = {"COME_NAME", "SUBCAT_NAME", "CAT_NAME"})
public class InOutCome {

    /**
     * ATTRIBUTE
     */

    @NonNull
    @ColumnInfo(name = "COME_NAME")
    private String m_COME_NAME;

    @NonNull
    @ColumnInfo(name = "CAT_NAME")
    private String m_CAT_NAME;

    @NonNull
    @ColumnInfo(name = "SUBCAT_NAME")
    private String m_SUBCAT_NAME;

    @ColumnInfo(name = "COME_AMOUNT")
    private double m_COME_AMOUNT;

    @NonNull
    @ColumnInfo(name = "COME_DATE")
    private LocalDate m_COME_DATE;

    /**
     * Constructor
     */

    public InOutCome(){

    }

    public InOutCome(String inOutName, String catName, String subCatName, Double amount, LocalDate date){
        Log.d("CREATION", "Instantiation of Category = " + inOutName);
        this.m_COME_NAME = inOutName;
        this.m_CAT_NAME = catName;
        this.m_SUBCAT_NAME = subCatName;
        this.m_COME_AMOUNT = amount;
        this.m_COME_DATE = date;
    }

    /**
     *  GETTER / SETTER
     */

    @NonNull
    public String getM_COME_NAME() {
        return m_COME_NAME;
    }

    public void setM_COME_NAME(@NonNull String m_COME_NAME) {
        this.m_COME_NAME = m_COME_NAME;
    }

    @NonNull
    public String getM_CAT_NAME() {
        return m_CAT_NAME;
    }

    public void setM_CAT_NAME(@NonNull String m_CAT_NAME) {
        this.m_CAT_NAME = m_CAT_NAME;
    }

    @NonNull
    public String getM_SUBCAT_NAME() {
        return m_SUBCAT_NAME;
    }

    public void setM_SUBCAT_NAME(@NonNull String m_SUBCAT_NAME) {
        this.m_SUBCAT_NAME = m_SUBCAT_NAME;
    }

    public double getM_COME_AMOUNT() {
        return m_COME_AMOUNT;
    }

    public void setM_COME_AMOUNT(double m_COME_AMOUNT) {
        this.m_COME_AMOUNT = m_COME_AMOUNT;
    }

    @NonNull
    public LocalDate getM_COME_DATE() {
        return m_COME_DATE;
    }

    public void setM_COME_DATE(@NonNull LocalDate m_COME_DATE) {
        this.m_COME_DATE = m_COME_DATE;
    }
}
