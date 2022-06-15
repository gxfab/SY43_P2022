package com.sucelloztm.sucelloz.models;

import androidx.room.Embedded;

public class SubCategoriesWithInfrequentSum {
    @Embedded
    public SubCategories subCategories;
    public int sumOfInfrequent;
    public String nameOfSubCategory;

    public SubCategories getSubCategory() {
        return subCategories;
    }

    public int getSumOfInfrequent() {
        return sumOfInfrequent;
    }

    public String getNameOfSubCategory() {
        return nameOfSubCategory;
    }
}
