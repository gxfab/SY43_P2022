package com.example.gestimali.envelope_tags

import androidx.lifecycle.LiveData

class EnvelopeTagsRepository(private val envelopeTagsDao: EnvelopeTagsDao) {

    val readAllData: LiveData<List<EnvelopeTags>> = envelopeTagsDao.readAllData()

    suspend fun addEnvelopeTag(envelopeTag: EnvelopeTags) {
        envelopeTagsDao.addEnvelopeTags(envelopeTag)
    }
}