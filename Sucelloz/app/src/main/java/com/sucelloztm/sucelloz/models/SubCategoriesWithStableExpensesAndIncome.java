package com.sucelloztm.sucelloz.models;

import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;


/**
 * Link between subcategory and stable expenses and income entities
 */
public class SubCategoriesWithStableExpensesAndIncome {
    @Embedded public SubCategories subCategories;
    @Relation(
            parentColumn = "id",
            entityColumn = "sub_categories_id"
    )
    public List<StableExpensesAndIncome> stableExpensesAndIncome;
}
