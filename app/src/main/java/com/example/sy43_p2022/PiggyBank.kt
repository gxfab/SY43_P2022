package com.example.sy43_p2022

import android.app.Application
import com.example.sy43_p2022.database.PiggyBankDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PiggyBank: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { PiggyBankDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { (database?.categoryDAO()) }
}