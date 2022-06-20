package com.sucelloztm.sucelloz.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;


/**
 * Link between subcategory and infrequent expenses and income entities
 */
public class SubCategoriesWithInfrequentExpensesAndIncome {
    @Embedded
    public SubCategories subCategories;
    @Relation(
            parentColumn = "id",
            entityColumn = "sub_categories_id"
    )
    public List<InfrequentExpensesAndIncome> infrequentExpensesAndIncome;
}
