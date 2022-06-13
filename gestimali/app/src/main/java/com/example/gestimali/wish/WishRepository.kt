package com.example.gestimali.wish

import androidx.lifecycle.LiveData

class WishRepository (private val dao: WishDao){
    val readAllData: LiveData<List<Wish>> = dao.readAllData()

    suspend fun addWish(wish: Wish) {
        dao.addWish(wish)
    }
}