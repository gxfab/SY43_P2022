package com.example.bokudarjan.envelope

import androidx.lifecycle.LiveData
import com.example.bokudarjan.expense.Expense
import com.example.bokudarjan.expense.ExpenseDAO

class EnvelopeRepository(private val envelopeDAO: EnvelopeDAO) {

    val readAllData: LiveData<List<Envelope>> = envelopeDAO.readAllData()

    suspend fun addUser(envelope: Envelope) {
        envelopeDAO.addEnvelope(envelope)
    }

    fun getMonthData(month: Int):LiveData<List<Envelope>>{
        return envelopeDAO.getMonthData(month)
    }

    fun getSumOfEnvelopes(month: Int):LiveData<Float>{
        return envelopeDAO.getSumOfEnvelopes(month)
    }

}