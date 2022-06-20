package com.example.fluz.ui.viewmodels

import androidx.lifecycle.*
import com.example.fluz.HashUtils
import com.example.fluz.data.entities.User
import com.example.fluz.data.repositories.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * View Model class for Login fragment
 *
 * @property repository
 */
class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    var errorMessage = MutableLiveData<String?>()
    var userId = MutableLiveData<Long?>()

    init {
        errorMessage.value = ""
        userId.value = -1
    }

    /**
     * Login a user by checking its credentials
     *
     * @param emailAddress
     * @param password
     * @return
     */
    fun login(emailAddress: String, password: String) = viewModelScope.launch {
        val retreivedUser = repository.oneByEmailAddress(emailAddress)

        if (retreivedUser.first() == null) {
            errorMessage.value = "The Email Address was not found"
        } else {
            val hashPassword = HashUtils.sha256(password)
            val user = retreivedUser.first()
            if (user.hash_password != hashPassword) {
                errorMessage.value = "The password is not correct"
            } else {
                userId.value = user.id.toLong()
            }
        }
    }

}

/**
 * Factory class used for dependency injection
 *
 * @property repository
 */
class LoginViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}