package com.example.fluz.data.relashionships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.User
import com.example.fluz.data.entities.UserCategory

data class UserWithCategories(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entity = Category::class,
        entityColumn = "id",
        associateBy = Junction(
            value = UserCategory::class,
            parentColumn = "userId",
            entityColumn = "categoryId"
        )
    )
    val categories: List<Category>
)
