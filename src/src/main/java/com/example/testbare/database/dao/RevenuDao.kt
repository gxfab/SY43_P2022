package com.example.testbare.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testbare.database.entities.Revenu

@Dao
interface RevenuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRevenu(revenu : Revenu)

    @Query("SELECT * FROM Revenu")
    suspend fun getAllRevenus() : List<Revenu>

    @Query("SELECT SUM(rev_montant) FROM Revenu WHERE rev_mois_id = :idMois")
    suspend fun getMontantTotalRevenuDuMois(idMois: Int) : Float

    @Query("SELECT * FROM Revenu WHERE rev_mois_id =:idMois")
    suspend fun getRevenusDuMois(idMois: Int) : List<Revenu>

    @Query("SELECT COUNT(rev_mois_id) FROM Revenu WHERE rev_mois_id = :idMois")
    suspend fun existMonthRevenu(idMois: Int) : Int

    @Query("DELETE FROM Revenu WHERE rev_id = :id")
    fun deleteRevenu(id :Int)
}