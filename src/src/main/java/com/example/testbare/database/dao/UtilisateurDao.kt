package com.example.testbare.database.dao

import androidx.room.*
import com.example.testbare.database.entities.Utilisateur

@Dao
interface UtilisateurDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(utilisateur: Utilisateur)

    @Query("SELECT COUNT(*) FROM Utilisateur")
    suspend fun existUser() : Int

    @Query("SELECT usr_mdp FROM Utilisateur")
    suspend fun getMdp() : String

    @Query("UPDATE Utilisateur SET usr_mdp = :newMdp")
    suspend fun updateMdp(newMdp : String)

    @Query("UPDATE Utilisateur SET usr_mail = :newMail")
    suspend fun updateMail(newMail : String)

    @Query("SELECT usr_mdp_actif FROM Utilisateur")
    suspend fun isMdpActif() : Boolean

    @Query("UPDATE Utilisateur SET usr_mdp_actif = :isActif")
    suspend fun updateMdpActif(isActif : Boolean)
}