package com.example.fluz.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fluz.data.entities.User
import com.example.fluz.data.repositories.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * View Model class for Parameters fragment
 *
 * @property userRepository
 */
class ParametersViewModel(private val userRepository: UserRepository) : ViewModel() {
    val currentUser: MutableLiveData<User> = MutableLiveData()

    /**
     * Retrieve current current connected user
     *
     * @param userId
     * @return
     */
    fun getCurrentUser(userId: Int) = viewModelScope.launch {
        val user = userRepository.oneUser(userId).first()

        currentUser.value = user
    }
}

/**
 * Factory class used for dependency injection
 *
 * @property repository
 */
class ParametersViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParametersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ParametersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}