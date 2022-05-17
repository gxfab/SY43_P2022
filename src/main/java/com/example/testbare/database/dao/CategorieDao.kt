package com.example.testbare.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testbare.database.entities.Categorie

@Dao
interface CategorieDao {
    @Query("SELECT * FROM Categorie")
    suspend fun getAllCategories() : List<Categorie>

    @Query("SELECT cat_categorie FROM Categorie")
    suspend fun getAllCategoriesNames() : List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategorie(categorie: Categorie)
}