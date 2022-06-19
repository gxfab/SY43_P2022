package com.example.testbare.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testbare.database.entities.Mois
import com.example.testbare.database.relations.MoisEtBudgets
import com.example.testbare.database.relations.MoisEtDepenses

@Dao
interface MoisDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMois(mois : Mois)

    @Query("SELECT mois_id FROM Mois WHERE mois_annee = :annee AND mois_mois = :mois")
    suspend fun getMoisId(annee: Int, mois:Int): Int

    @Query("SELECT * FROM Mois")
    suspend fun getAllMois() : List<Mois>

    @Query( "SELECT * FROM Mois WHERE mois_mois = :mois AND mois_annee = :annee")
    suspend fun getDepensesByMois(mois : Int, annee : Int): MoisEtDepenses

    @Query("SELECT * FROM Mois WHERE mois_id = :mois_id")
    suspend fun getBudgetsByMois(mois_id : Int) : MoisEtBudgets

    @Query("SELECT COUNT(*) FROM Mois WHERE mois_annee = :annee AND mois_mois = :mois")
    suspend fun existMonth(annee: Int, mois:Int): Int
}