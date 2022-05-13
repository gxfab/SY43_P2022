package com.example.fluz.data.relashionships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.User

data class UserWithBudgets(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val budgets: List<Budget>
)