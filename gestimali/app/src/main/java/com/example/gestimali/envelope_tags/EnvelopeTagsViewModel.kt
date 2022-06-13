package com.example.gestimali.envelope_tags

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gestimali.database.BudgetDatabase
import com.example.gestimali.envelope.Envelope
import com.example.gestimali.envelope.EnvelopeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnvelopeTagsViewModel (application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<EnvelopeTags>>
    private val repository: EnvelopeTagsRepository


    init {
        val dao = BudgetDatabase.getDatabase(application).envelopeTagsDao()
        repository = EnvelopeTagsRepository(dao)
        readAllData = repository.readAllData
    }

    fun addEnvelopeTag(tag: EnvelopeTags){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEnvelopeTag(tag)
        }
    }
}