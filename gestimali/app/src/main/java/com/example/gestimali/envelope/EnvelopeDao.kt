package com.example.gestimali.envelope

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface EnvelopeDao {
    @Insert(onConflict = IGNORE)
    suspend fun addEnvelope(envelope : Envelope)

    @Query("SELECT * FROM T_enveloppe ORDER BY env_id ASC")
    fun readAllData() : LiveData<List<Envelope>>

}