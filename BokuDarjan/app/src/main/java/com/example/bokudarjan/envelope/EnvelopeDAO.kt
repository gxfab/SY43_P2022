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

    @Query("SELECT * FROM envelope_table WHERE month = :month ORDER BY name")
    fun getMonthData(month: Int): LiveData<List<Envelope>>;

    @Query("SELECT SUM(amount) FROM envelope_table WHERE categoryName != 'Bénéfices' AND month = :month ")
    fun getSumOfEnvelopes(month: Int): LiveData<Float>;

    @Query("SELECT SUM(amount) FROM envelope_table WHERE  categoryName = :name  AND month =:month")
    fun getSumOfEnvelopeByCategory(name: String, month: Int):LiveData<Float>;
}