package com.example.sy43_p2022.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryAndSubCategory(
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "catid",
        entityColumn = "categoryId"
    )
    val subcategory: List<SubCategory>
)