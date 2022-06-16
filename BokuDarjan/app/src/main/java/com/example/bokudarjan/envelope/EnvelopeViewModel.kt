package com.example.bokudarjan.envelope

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bokudarjan.database.BokudarjanDatabase
import com.example.bokudarjan.envelope.Envelope
import com.example.bokudarjan.envelope.EnvelopeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Month


class EnvelopeViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Envelope>>
    private val repository: EnvelopeRepository


    init {
        val envelopeDao = BokudarjanDatabase.getDatabase(application).envelopeDao()
        repository = EnvelopeRepository(envelopeDao)
        readAllData = repository.readAllData
    }

    fun addEnvelope(envelope: Envelope){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(envelope)
        }
    }

    fun getMonthData(month: Int): LiveData<List<Envelope>> {
        return repository.getMonthData(month);
    }

    fun getSumOfEnvelopes(month: Int):LiveData<Float>{
        return repository.getSumOfEnvelopes(month)
    }

    fun getSumOfEnvelopeByCategory(name: String, month: Int):LiveData<Float>{
        return repository.getSumOfEnvelopeByCategory(name, month)
    }

}
