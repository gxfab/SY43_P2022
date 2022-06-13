package com.example.gestimali.tag

import androidx.lifecycle.LiveData

class TagRepository (private val dao: TagDao) {
    val readAllData: LiveData<List<Tag>> = dao.readAllData()

    suspend fun addTag(tag: Tag) {
        dao.addTag(tag)
    }
}