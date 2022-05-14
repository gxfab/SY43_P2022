package com.sucelloztm.sucelloz.models;

import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;



public class SubCategoriesWithInfrequentExpensesAndIncome {
    @Embedded public SubCategories subCategories;
    @Relation(
            parentColumn = "id",
            entityColumn = "sub_categories_id"
    )
    public List<InfrequentExpensesAndIncome> infrequentExpensesAndIncome;
}
