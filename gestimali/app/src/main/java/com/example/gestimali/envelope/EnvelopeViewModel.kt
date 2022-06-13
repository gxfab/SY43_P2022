package com.example.gestimali.envelope

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gestimali.database.BudgetDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnvelopeViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Envelope>>
    private val repository: EnvelopeRepository


    init {
        val envelopeDao = BudgetDatabase.getDatabase(application).envelopeDao()
        repository = EnvelopeRepository(envelopeDao)
        readAllData = repository.readAllData
    }

    fun addEnvelope(envelope: Envelope){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEnvelope(envelope)
        }
    }

}