package com.example.fluz.ui.viewmodels

import androidx.lifecycle.*
import com.example.fluz.HashUtils
import com.example.fluz.data.entities.User
import com.example.fluz.data.repositories.UserRepository
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    var userId = MutableLiveData<Long?>()
    var errorMessage = MutableLiveData<String?>()

    init {
        userId.value = -1
        errorMessage.value = ""
    }

    val allUsers = repository.allUsers().asLiveData()

    fun register(email_address: String, password: String, username: String, currency: String, budget_start_day: Int) =
        viewModelScope.launch {
            val hashPassword = HashUtils.sha256(password)
            try {

                val insertedUserId = repository.insert(
                    User(
                        email_address = email_address,
                        hash_password = hashPassword,
                        username = username,
                        currency = currency,
                        budget_start_day = budget_start_day,
                        created_at = System.currentTimeMillis().toString()
                    )
                )

                userId.value = insertedUserId


            } catch (e: RuntimeException) {
                errorMessage.value = "This email address is already taken"
            }

        }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
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