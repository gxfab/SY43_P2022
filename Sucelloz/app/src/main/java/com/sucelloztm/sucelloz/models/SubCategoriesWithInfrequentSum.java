package com.sucelloztm.sucelloz.models;

import androidx.room.Embedded;

/**
 * entity for the subcategory with infrequent sum of the dao
 */
public class SubCategoriesWithInfrequentSum {
    @Embedded
    public SubCategories subCategories;
    public int sumOfInfrequent;
    public String nameOfSubCategory;

    /**
     * getter
     * @return subcategory
     */
    public SubCategories getSubCategory() {
        return subCategories;
    }

    /**
     * getter
     * @return sum
     */
    public int getSumOfInfrequent() {
        return sumOfInfrequent;
    }

    /**
     * getter
     * @return name
     */
    public String getNameOfSubCategory() {
        return nameOfSubCategory;
    }
}
