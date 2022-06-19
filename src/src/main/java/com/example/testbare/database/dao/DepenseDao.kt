package com.example.testbare.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testbare.database.entities.Depense
import com.example.testbare.database.entities.DepenseCategorise

@Dao
interface DepenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDepense(depense : Depense)

    @Query("SELECT * FROM Depense WHERE dep_mois_id = :idMois ORDER BY dep_date DESC")
    suspend fun getAllDepensesDateDesc(idMois : Int) : List<Depense>

    @Query("SELECT * FROM Depense WHERE dep_mois_id = :idMois ORDER BY dep_date ASC")
    suspend fun getAllDepensesDateAsc(idMois : Int) : List<Depense>

    @Query("SELECT * FROM Depense WHERE dep_mois_id = :idMois ORDER BY dep_montant DESC")
    suspend fun getAllDepensesMontantDesc(idMois : Int) : List<Depense>

    @Query("SELECT * FROM Depense WHERE dep_mois_id = :idMois ORDER BY dep_montant ASC")
    suspend fun getAllDepensesMontantAsc(idMois : Int) : List<Depense>

    @Query("SELECT * FROM Depense WHERE dep_mois_id = :idMois")
    suspend fun getDepenseDuMois(idMois : Int) : List<Depense>

    @Query("SELECT COUNT(dep_mois_id) FROM Depense WHERE dep_mois_id = :idMois")
    suspend fun existMonthDepense(idMois: Int) : Int

    @Query("SELECT dep_categorie AS 'categorie', SUM(dep_montant) AS 'montant' FROM Depense WHERE dep_mois_id = :idMois GROUP BY dep_categorie")
    suspend fun getDepenseDuMoisRegroupe(idMois : Int) : List<DepenseCategorise>

    @Query("SELECT * FROM Depense WHERE dep_mois_id = :idMois AND dep_categorie IN (:listCategories)")
    suspend fun getDepenseByCategories(listCategories : List<String>, idMois: Int) : List<Depense>

    @Query("DELETE FROM Depense WHERE dep_id = :id")
    fun deleteDepense(id : Int)
}