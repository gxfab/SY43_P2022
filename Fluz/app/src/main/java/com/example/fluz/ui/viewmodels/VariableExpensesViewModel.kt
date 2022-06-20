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

/**
 * View Model class for VariableExpenses fragment
 *
 * @property userRepository
 * @property categoriesRepository
 * @property budgetItemRepository
 * @property budgetRepository
 * @property transactionRepository
 */
class VariableExpensesViewModel(
    private val userRepository: UserRepository,
    private val categoriesRepository: CategoryRepository,
    private val budgetItemRepository: BudgetItemRepository,
    private val budgetRepository: BudgetRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    val allCategories = categoriesRepository.allCategories().asLiveData()
    val income: MutableLiveData<Int> = MutableLiveData()
    val expenses: MutableLiveData<Int> = MutableLiveData()
    val budgets: MutableLiveData<List<Budget>> = MutableLiveData()

    val budgetItems: MutableLiveData<List<BudgetItemAndCategory>> = MutableLiveData();

    val errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        income.value = 0;
        expenses.value = 0;
        budgetItems.value = emptyList()
        errorMessage.value = ""
    }

    /**
     * Get all budgets of the connected user
     *
     * @param userId
     * @return
     */
    fun getBudgets(userId: Int) = viewModelScope.launch {
        val localBudgets: MutableList<Budget> = mutableListOf()

        val userWithBudgets = userRepository.oneWithBudgets(userId).first()
        for (userWithBudget in userWithBudgets.budgets) {
            localBudgets.add(userWithBudget)
        }

        budgets.value = localBudgets.toList()
    }

    /**
     * Insert a new budget in the database
     *
     * @param userId
     * @return
     */
    fun createBudget(userId: Int) = viewModelScope.launch {
        val currentUser = userRepository.oneUser(userId).first()

        val currentDate = Calendar.getInstance()
        if (currentUser.budget_start_day > currentDate.get(Calendar.DAY_OF_MONTH)) {
            currentDate.add(Calendar.MONTH, -1)
        }

        val budgetStartMonth = SimpleDateFormat("MMM").format(currentDate.time)

        val startDate: String = currentUser.budget_start_day.toString() +  " " + budgetStartMonth + " " + SimpleDateFormat("YYYY").format(currentDate.time)

        currentDate.add(Calendar.MONTH, 1)

        val endDate: String = currentUser.budget_start_day.toString() +  " " + SimpleDateFormat("MMM").format(currentDate.time) + " " + SimpleDateFormat("YYYY").format(currentDate.time)

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

        getBudgets(userId)
    }

    /**
     * Insert a new budget item in the database
     *
     * @param amount
     * @param category
     */
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

    /**
     * Delete a budget item with its category
     *
     * @param category
     */
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

    /**
     * check if a category is already in budget
     *
     * @param category
     * @return
     */
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

    /**
     * Checks if total amount of expenses is superior to income
     *
     * @param amount
     * @return
     */
    private fun checkAmountSuperiorToIncome(amount: Int): Boolean {
        return expenses.value!! + amount > income.value!!
    }

    /**
     * Retrieve total income amount
     *
     * @param userId
     * @return
     */
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

    /**
     * Retrieve total expenses of a user
     *
     * @param userId
     * @return
     */
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

/**
 * Factory class used for dependency injection
 *
 * @property userRepository
 * @property categoriesRepository
 * @property budgetItemRepository
 * @property budgetRepository
 * @property transactionRepository
 */
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