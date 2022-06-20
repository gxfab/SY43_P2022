package com.example.fluz.data.repositories

import androidx.annotation.WorkerThread
import com.example.fluz.data.dao.UserDao
import com.example.fluz.data.entities.User
import com.example.fluz.data.relashionships.UserWithBudgets
import com.example.fluz.data.relashionships.UserWithCategories
import com.example.fluz.data.relashionships.UserWithTransactions
import kotlinx.coroutines.flow.Flow

/**
 * User repository
 */
class UserRepository(private val userDao: UserDao) {

    fun allUsers(): Flow<List<User>> = userDao.getAll()
    fun oneUser(userId: Int): Flow<User> = userDao.get(userId)
    fun oneByEmailAddress(emailAddress: String): Flow<User> = userDao.getByEmailAddress(emailAddress)
    fun oneWithBudgets(userId: Int): Flow<UserWithBudgets> = userDao.getWithBudgets(userId)
    fun oneWithCategories(userId: Int): Flow<UserWithCategories> = userDao.getWithCategories(userId)
    fun oneWithTransactions(userId: Int): Flow<UserWithTransactions> = userDao.getWithTransactions(userId)


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User): Long {
        return userDao.insert(user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        userDao.deleteAll()
    }


}