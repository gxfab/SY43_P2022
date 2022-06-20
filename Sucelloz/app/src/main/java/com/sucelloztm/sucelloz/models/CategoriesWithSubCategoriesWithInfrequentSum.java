package com.sucelloztm.sucelloz.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/**
 * Link between categories, subcategories and infrequent sums
 */
public class CategoriesWithSubCategoriesWithInfrequentSum {
    @Embedded
    public Categories category;
    public int sumOfSubCategory;
    public String nameOfCategory;
    @Relation(parentColumn = "categories_id",
            entityColumn = "id_of_category"
    )
    List<CategoriesWithSubCategoriesWithInfrequentSum> categoriesWithSubCategoriesWithInfrequentSum;


    /**
     * Getter
     *
     * @return category
     */
    public Categories getCategory() {
        return category;
    }

    /**
     * Getter
     *
     * @return sum of the sub-category
     */
    public int getSumOfSubCategory() {
        return sumOfSubCategory;
    }

    /**
     * Getter
     *
     * @return get name of the Category
     */
    public String getNameOfCategory() {
        return nameOfCategory;
    }
}
