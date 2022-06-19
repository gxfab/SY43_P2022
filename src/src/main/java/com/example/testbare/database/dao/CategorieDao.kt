package com.example.testbare.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testbare.database.entities.Categorie
import com.example.testbare.database.relations.CategorieEtBudgets
import com.example.testbare.database.relations.CategorieEtDepenses

@Dao
interface CategorieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategorie(categorie: Categorie)

    @Query("SELECT cat_categorie FROM Categorie")
    suspend fun getAllCategoriesNames() : List<String>

    @Query( "SELECT * FROM Categorie WHERE cat_categorie = :categorie")
    suspend fun getDepensesByCategorie(categorie: String): CategorieEtDepenses

    @Query("SELECT * FROM Categorie WHERE cat_categorie = :categorie")
    suspend fun getBudgetsByCategorie(categorie: String) : CategorieEtBudgets

    @Query("SELECT * FROM Categorie")
    suspend fun getAllCategorie() : List<Categorie>
}