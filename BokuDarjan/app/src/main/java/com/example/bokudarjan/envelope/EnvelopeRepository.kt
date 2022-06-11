package com.example.bokudarjan.envelope

import androidx.lifecycle.LiveData
import com.example.bokudarjan.expense.Expense
import com.example.bokudarjan.expense.ExpenseDAO

class EnvelopeRepository(private val envelopeDAO: EnvelopeDAO) {

    val readAllData: LiveData<List<Envelope>> = envelopeDAO.readAllData()
    val sumOfEnvelopes: LiveData<Int> = envelopeDAO.getSumOfEnvelopes()

    suspend fun addUser(envelope: Envelope) {
        envelopeDAO.addEnvelope(envelope)
    }

}