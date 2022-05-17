package com.example.testbare.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testbare.database.entities.Depense

@Dao
interface DepenseDao {
    @Query("SELECT * FROM Depense")
    suspend fun getAllDepenses() : List<Depense>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDepense(depense : Depense)
}