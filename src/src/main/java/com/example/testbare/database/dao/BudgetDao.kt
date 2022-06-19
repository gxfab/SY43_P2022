package com.example.testbare.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testbare.database.entities.Budget

@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget : Budget)

    @Query("SELECT * FROM Budget")
    suspend fun getAllBudgets() : List<Budget>

    @Query("SELECT * FROM Budget WHERE bud_mois_id = :idMois")
    suspend fun getBudgetDuMois(idMois : Int) : List<Budget>

    @Query("SELECT SUM(bud_montant) FROM Budget WHERE bud_mois_id = :idMois")
    suspend fun getMontantTotalBudgetMois(idMois: Int) : Float

    @Query("SELECT COUNT(bud_mois_id) FROM Budget WHERE bud_mois_id = :idMois")
    suspend fun existMonthBudget(idMois: Int) : Int

    @Query("UPDATE Budget SET bud_montant = :montant, bud_categorie = :categorie WHERE bud_id = :id")
    suspend fun updateBudget(id : Int, montant : Float, categorie : String)

    @Query("DELETE FROM Budget WHERE bud_id = :budgetId")
    fun deleteBudget(budgetId : Int)
}