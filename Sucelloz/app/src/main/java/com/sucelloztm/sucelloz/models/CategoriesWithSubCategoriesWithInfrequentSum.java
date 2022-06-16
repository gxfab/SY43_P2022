package com.sucelloztm.sucelloz.models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

public class CategoriesWithSubCategoriesWithInfrequentSum {
    @Embedded
    public Categories category;
    public int sumOfSubCategory;
    public String nameOfCategory;
    @Relation(parentColumn = "categories_id",
            entityColumn = "id_of_category"
    )
    List<CategoriesWithSubCategoriesWithInfrequentSum> categoriesWithSubCategoriesWithInfrequentSum;





    public Categories getCategory() {
        return category;
    }

    public int getSumOfSubCategory() {
        return sumOfSubCategory;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }
}
