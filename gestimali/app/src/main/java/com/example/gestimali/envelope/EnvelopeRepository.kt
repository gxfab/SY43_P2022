package com.example.gestimali.envelope

import androidx.lifecycle.LiveData


class EnvelopeRepository(private val envelopeDao: EnvelopeDao) {

    val readAllData: LiveData<List<Envelope>> = envelopeDao.readAllData()

    suspend fun addEnvelope(envelope: Envelope) {
        envelopeDao.addEnvelope(envelope)
    }

}