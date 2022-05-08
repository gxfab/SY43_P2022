package net.yolopix.moneyz.model

import androidx.lifecycle.ViewModel
import net.yolopix.moneyz.model.database.AccountDao

class AccountViewModel(private val accountDao: AccountDao) : ViewModel() {

}