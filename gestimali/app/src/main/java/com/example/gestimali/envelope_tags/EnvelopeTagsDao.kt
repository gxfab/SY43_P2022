package com.example.gestimali.envelope_tags

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EnvelopeTagsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addEnvelopeTags(envTags : EnvelopeTags)

    @Query("SELECT * FROM T_enveloppe_tags ORDER BY env_id ASC")
    fun readAllData() : LiveData<List<EnvelopeTags>>
}