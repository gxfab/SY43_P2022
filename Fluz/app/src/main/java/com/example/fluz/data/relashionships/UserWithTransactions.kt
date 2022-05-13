package com.example.fluz.data.relashionships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fluz.data.entities.Category
import com.example.fluz.data.entities.Transaction
import com.example.fluz.data.entities.User
import com.example.fluz.data.entities.UserCategory

data class UserWithTransactions(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId",
    )
    val transactions: List<Transaction>
)
