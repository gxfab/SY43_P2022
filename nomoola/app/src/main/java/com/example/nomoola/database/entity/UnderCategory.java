package com.example.nomoola.database.entity;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "T_UNDERCATEGORY", primaryKeys = {"UNDERCAT_NAME", "CAT_NAME"})
public class UnderCategory {

    /**
     * ATTRIBUTE
     */

    @NonNull
    @ColumnInfo(name = "CAT_NAME")
    private String m_CAT_NAME;

    @NonNull
    @ColumnInfo(name = "UNDERCAT_NAME")
    private String m_UNDERCAT_NAME;


    /**
     * CONSTRUCTOR
     */

    public UnderCategory(){
    }

    public UnderCategory(@NonNull String categoryName, @NonNull String UnderCategoryName){
        Log.d("CREATION", "Instantiation of UnderCategory = "+UnderCategoryName);
        this.m_CAT_NAME = categoryName;
        this.m_UNDERCAT_NAME = UnderCategoryName;
    }

    /**
     * GETTER / SETTER
     */
    public String getM_CAT_NAME() {
        return m_CAT_NAME;
    }

    public void setM_CAT_NAME(String m_CAT_NAME) {
        this.m_CAT_NAME = m_CAT_NAME;
    }

    @NonNull
    public String getM_UNDERCAT_NAME() {
        return m_UNDERCAT_NAME;
    }

    public void setM_UNDERCAT_NAME(@NonNull String m_UNDERCAT_NAME) {
        this.m_UNDERCAT_NAME = m_UNDERCAT_NAME;
    }
}
