package com.example.lafo_cheuse.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Budget(budgetCategory: Category?, duration: Duration?) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "budgetId")
    var budgetId: Long = 0

    @Embedded(prefix="category_")
    var budgetCategory: Category? = null

    @ColumnInfo(name = "duration")
    var duration: Duration = Duration.MONTH

    init {
        if(budgetCategory != null)
            this.budgetCategory = budgetCategory
        if(duration != null)
            this.duration = duration
    }
}