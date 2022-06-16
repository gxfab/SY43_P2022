package com.sucelloztm.sucelloz.models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "sub_categories_infrequent_sum",
        foreignKeys = {@ForeignKey(entity = Categories.class,
        parentColumns = "category_id",
        childColumns = "id_of_category",
        onDelete = ForeignKey.CASCADE)}
)
public class SubCategoriesWithInfrequentSum {
    @Embedded
    public SubCategories subCategory;
    @ColumnInfo(name = "sum_of_infrequent")
    public int sumOfInfrequent;

    @PrimaryKey
    @ColumnInfo(name = "id_of_category")
    public long idOfCategory;

    public String nameOfSubCategory;

    @ColumnInfo(name = "sign")
    public String sign;



    public SubCategories getSubCategory() {
        return subCategory;
    }

    public int getSumOfInfrequent() {
        return sumOfInfrequent;
    }

    public String getNameOfSubCategory() {
        return nameOfSubCategory;
    }

    public long getIdOfCategory() {
        return idOfCategory;
    }

    public String getSign() {
        return sign;
    }
}
