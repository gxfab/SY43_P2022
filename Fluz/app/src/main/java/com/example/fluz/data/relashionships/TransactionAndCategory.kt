package com.example.fluz.data.relashionships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.Transaction


data class TransactionAndCategory(
    @Embedded val transaction: Transaction,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: Category
)
