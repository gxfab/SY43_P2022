package com.example.fluz.data.relashionships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fluz.data.entities.BudgetItem
import com.example.fluz.data.entities.Category

/**
 * Relationship between budget items and category
 */
data class BudgetItemAndCategory(
    @Embedded val budgetItem: BudgetItem,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: Category
)
