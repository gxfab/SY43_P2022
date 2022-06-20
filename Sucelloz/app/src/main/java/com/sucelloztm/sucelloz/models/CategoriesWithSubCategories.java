package com.sucelloztm.sucelloz.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/**
 * Link between category and subcategory entities
 */
public class CategoriesWithSubCategories {
    @Embedded public Categories categories;
    @Relation(
            parentColumn = "category_id",
            entityColumn = "categories_id"
    )
    public List<SubCategories> subCategories;
}
