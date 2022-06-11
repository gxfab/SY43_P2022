package com.example.bokudarjan.saving

import androidx.lifecycle.LiveData

class SavingRepository(private val savingDAO: SavingDAO) {

    val readAllData: LiveData<List<Saving>> = savingDAO.readAllData()

    suspend fun addSaving(saving : Saving) {
        savingDAO.addSaving(saving)
    }

    suspend fun updateSum(value: Float, name: String){
        savingDAO.updateSum(value,name);
    }
}