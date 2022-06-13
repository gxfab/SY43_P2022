package com.example.gestimali.moneysaved

import androidx.lifecycle.LiveData

class MoneySavedRepository  (private val dao: MoneySavedDao){
    val readAllData: LiveData<List<MoneySaved>> = dao.readAllData()

    suspend fun addMoneySaved(money: MoneySaved) {
        dao.addMoneySaved(money)
    }
}