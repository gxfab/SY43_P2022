package com.example.fluz.ui

import androidx.lifecycle.*
import com.example.fluz.HashUtils
import com.example.fluz.data.AppDatabase
import com.example.fluz.data.entities.User
import com.example.fluz.data.repositories.UserRepository
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    var userId: Long? = null
    var errorMessage: MutableLiveData<String>? = null

    val allUsers = repository.allUsers().asLiveData()

    fun register(email_address: String, password: String, username: String, currency: String) =
        viewModelScope.launch {
            val hashPassword = HashUtils.sha256(password)
            try {
                userId = repository.insert(
                    User(
                        email_address = email_address,
                        hash_password = hashPassword,
                        username = username,
                        currency = currency,
                        created_at = System.currentTimeMillis().toString()
                    )
                )


            } catch (e: RuntimeException) {
                errorMessage = MutableLiveData("This email address is already taken")
            }

        }
}

class RegisterViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}