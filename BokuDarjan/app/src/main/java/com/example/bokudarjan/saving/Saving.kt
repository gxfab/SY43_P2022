package com.example.bokudarjan.saving

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saving")
/**
 * A database entity class used to represent savings.
 */
class Saving(@PrimaryKey() var name: String, var to_reach: Float) {
    var current: Float = 0f;

    init {
        this.current = 0f
    }
}