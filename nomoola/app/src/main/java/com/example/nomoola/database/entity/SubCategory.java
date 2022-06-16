package com.example.nomoola.database.entity;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "T_SUBCATEGORY")
public class SubCategory {

    /**
     * ATTRIBUTE
     */

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "SUBCAT_ID")
    private int m_SUBCAT_ID;

    @NonNull
    @ColumnInfo(name = "CAT_ID")
    private int m_CAT_ID;

    @NonNull
    @ColumnInfo(name = "SUBCAT_NAME")
    private String m_SUBCAT_NAME;


    /**
     * CONSTRUCTOR
     */

    public SubCategory(){
    }

    public SubCategory(@NonNull int categoryID, @NonNull String SubCategory){
        Log.d("CREATION", "Instantiation of UnderCategory = "+SubCategory);
        this.m_CAT_ID = categoryID;
        this.m_SUBCAT_NAME = SubCategory;
    }

    /**
     * METHODS
     */

    @Override
    public String toString(){
        return this.m_SUBCAT_NAME;
    }

    /**
     * GETTER / SETTER
     */
    public int getM_CAT_ID() {
        return m_CAT_ID;
    }

    public void setM_CAT_ID(int m_CAT_ID) {
        this.m_CAT_ID = m_CAT_ID;
    }

    @NonNull
    public String getM_SUBCAT_NAME() {
        return m_SUBCAT_NAME;
    }

    public void setM_SUBCAT_NAME(@NonNull String m_SUBCAT_NAME) {
        this.m_SUBCAT_NAME = m_SUBCAT_NAME;
    }

    public int getM_SUBCAT_ID() {
        return m_SUBCAT_ID;
    }

    public void setM_SUBCAT_ID(int m_SUBCAT_ID) {
        this.m_SUBCAT_ID = m_SUBCAT_ID;
    }
}
