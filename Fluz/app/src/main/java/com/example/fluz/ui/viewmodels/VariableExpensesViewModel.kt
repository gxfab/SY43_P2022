package com.example.fluz.ui.viewmodels

import androidx.lifecycle.*
import com.example.fluz.data.entities.Budget
import com.example.fluz.data.entities.BudgetItem
import com.example.fluz.data.entities.Category
import com.example.fluz.data.relashionships.BudgetItemAndCategory
import com.example.fluz.data.relashionships.TransactionAndCategory
import com.example.fluz.data.relashionships.UserWithTransactions
import com.example.fluz.data.repositories.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class VariableExpensesViewModel(
    private val userRepository: UserRepository,
    private val categoriesRepository: CategoryRepository,
    private val budgetItemRepository: BudgetItemRepository,
    private val budgetRepository: BudgetRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    val allCategories = categoriesRepository.allCategories().asLiveData()
    val income: MutableLiveData<Int> = MutableLiveData();
    val expenses: MutableLiveData<Int> = MutableLiveData();

    val budgetItems: MutableLiveData<List<BudgetItemAndCategory>> = MutableLiveData();

    val errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        income.value = 0;
        expenses.value = 0;
        budgetItems.value = emptyList()
        errorMessage.value = ""
    }

    fun createBudget(userId: Int) = viewModelScope.launch {
        val currentUser = userRepository.oneUser(userId).first()

        val currentDate = Calendar.getInstance()
        val currentMonth = SimpleDateFormat("MMM").format(currentDate.time)

        val startDate: String = currentUser.budget_start_day.toString() +  " " + currentMonth + " " + SimpleDateFormat("YYYY").format(currentDate.time)

        currentDate.add(Calendar.MONTH, 1)
        val nextMonth = SimpleDateFormat("MMM").format(currentDate.time)

        val endDate: String = ((currentUser.budget_start_day) - 1).toString() +  " " + nextMonth + " " + SimpleDateFormat("YYYY").format(currentDate.time)

//        println("Start date : $startDate")
//        println("End date : $endDate")

        val budget = Budget(start_date = startDate, end_date = endDate, total_amount = income.value!!, userId = userId)
        budgetRepository.insert(budget)

        val lastBudget = userRepository.oneWithBudgets(currentUser.id).first().budgets.last()

        for (budgetItem in budgetItems.value!!) {
            val newBudgetItem = budgetItem.budgetItem
            newBudgetItem.budgetId = lastBudget.id
            budgetItemRepository.insert(newBudgetItem)
        }
    }

    fun addBudgetItem(amount: Int, category: Category) {
        val newList = budgetItems.value?.toMutableList()
        val newBudgetItem = BudgetItem(amount = amount, budgetId = 0, categoryId = category.id)

        val newBudgetItemAndCategory = BudgetItemAndCategory(newBudgetItem, category)

        if (checkCategoryAlreadyPresent(category)) {
            errorMessage.value = "This category was already assigned"
        } else if (checkAmountSuperiorToIncome(amount)) {
            errorMessage.value = "Expenses cannot be more than income"
        } else {
            newList?.add(newBudgetItemAndCategory)
            budgetItems.value = newList?.toList()
            errorMessage.value = ""
        }

    }

    fun deleteBudgetItem(category: Category) {
        val newList = budgetItems.value?.toMutableList()
        for (budgetItem in budgetItems.value!!) {
            if (budgetItem.category.title == category.title) {
                newList?.remove(budgetItem)
                break
            }
        }


        budgetItems.value = newList?.toList()
    }

    private fun checkCategoryAlreadyPresent(category: Category): Boolean {
        var res: Boolean = false
        for (budgetItem in budgetItems.value!!) {
            if (budgetItem.category.id == category.id) {
                res = true
                break
            }
        }

        return res
    }

    private fun checkAmountSuperiorToIncome(amount: Int): Boolean {
        return expenses.value!! + amount > income.value!!
    }

    fun getTotalIncome(userId: Int) = viewModelScope.launch {
        var userWithTransactions: UserWithTransactions = userRepository.oneWithTransactions(userId).first()

        val transactions: MutableList<TransactionAndCategory> = mutableListOf()
        for (transaction in userWithTransactions.transactions) {
            if (transaction.type == "income") {
                val item = transactionRepository.oneWithCategory(transaction.id)
                transactions.add(item.first())
            }

        }
        var totalIncome = 0;

        for (transaction in transactions) {
            totalIncome += transaction.transaction.amount
        }

        income.value = totalIncome

    }

    fun getTotalExpenses(userId: Int) = viewModelScope.launch {
        var userWithTransactions: UserWithTransactions = userRepository.oneWithTransactions(userId).first()

        val transactions: MutableList<TransactionAndCategory> = mutableListOf()
        for (transaction in userWithTransactions.transactions) {
            if (transaction.type == "expense") {
                val item = transactionRepository.oneWithCategory(transaction.id)
                transactions.add(item.first())
            }

        }
        var totalExpenses = 0;

        for (transaction in transactions) {
            totalExpenses += transaction.transaction.amount
        }

        for (budgetItem in budgetItems.value!!) {
            totalExpenses += budgetItem.budgetItem.amount
        }

        expenses.value = totalExpenses

    }

}

class VariableExpensesViewModelFactory(
    private val userRepository: UserRepository,
    private val categoriesRepository: CategoryRepository,
    private val budgetItemRepository: BudgetItemRepository,
    private val budgetRepository: BudgetRepository,
    private val transactionRepository: TransactionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VariableExpensesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VariableExpensesViewModel(
                userRepository,
                categoriesRepository,
                budgetItemRepository,
                budgetRepository,
                transactionRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}