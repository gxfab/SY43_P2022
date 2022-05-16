package com.example.fluz.data.relashionships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.SubCategory

data class CategoriesWithSubCategories(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId",
    )
    val subCategories: List<SubCategory>
)
