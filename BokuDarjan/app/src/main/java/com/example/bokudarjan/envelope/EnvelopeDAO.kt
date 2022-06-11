package com.example.bokudarjan.envelope

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bokudarjan.envelope.Envelope

@Dao
interface EnvelopeDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEnvelope(Envelope: Envelope);

    @Query("SELECT * FROM envelope_table ORDER BY name")
    fun readAllData(): LiveData<List<Envelope>>;

    @Query("SELECT SUM(amount) FROM envelope_table WHERE categoryName != 'Bénéfices' ")
    fun getSumOfEnvelopes(): LiveData<Float>;
}