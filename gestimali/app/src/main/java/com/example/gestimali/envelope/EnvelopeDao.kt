package com.example.gestimali.envelope

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EnvelopeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addEnvelope(envelope : Envelope)

    @Query("SELECT * FROM T_envelope ORDER BY env_id ASC")
    fun readAllData() : LiveData<List<Envelope>>

}