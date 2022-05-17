package com.example.fluz.data.relashionships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.User
import com.example.fluz.data.entities.UserCategory

data class CategoriesWithUsers(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entity = User::class,
        entityColumn = "id",
        associateBy = Junction(
            value = UserCategory::class,
            parentColumn = "categoryId",
            entityColumn = "userId"
        )
    )
    val users: List<User>
)
