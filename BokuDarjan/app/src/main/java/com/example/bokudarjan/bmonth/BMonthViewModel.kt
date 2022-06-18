package com.example.bokudarjan.bmonth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bokudarjan.database.BokudarjanDatabase
import com.example.bokudarjan.expense.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel class for the [BMonthDAO]
 */
class BMonthViewModel(application: Application): AndroidViewModel(application) {
    val dao : BMonthDAO
    val readAllData: LiveData<List<BMonth>>

    init{
        dao = BokudarjanDatabase.getDatabase(application).BMonthDAO()
        readAllData = dao.readAllData()
    }

    fun getMonth(month : Int):LiveData<BMonth>{
        return dao.getMonth(month)
    }

    fun addMonth(month : BMonth){
        viewModelScope.launch(Dispatchers.IO) {
            dao.addMonth(month)
        }
    }

}