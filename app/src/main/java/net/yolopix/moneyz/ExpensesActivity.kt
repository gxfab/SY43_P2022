package net.yolopix.moneyz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Account
import net.yolopix.moneyz.model.entities.Month
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * This activity enables the user to view expenses sorted by category, for a selected account
 * The account should be passed as extra value
 */
class ExpensesActivity : AppCompatActivity() {

    /** The account object representing the current opened account */
    private lateinit var account: Account

    /** The current month in which expenses are added */
    private lateinit var currentMonth: Month

    /** Database */
    private lateinit var db: AppDatabase

    /** The database identifier of the loaded account, null if no account has been loaded yet */
    private var accountUid: Int? = null

    // Widgets
    private lateinit var expensesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses)

        // Get the database object
        db = DatabaseFactory.getDB(applicationContext)

        // Fetch the account from the uid passed to the activity as extra
        accountUid = intent.getIntExtra(EXTRA_MESSAGE, 0)
        lifecycleScope.launch {
            if (accountUid == null) {
                finish()
            } else {
                loadAccount()
            }
        }

        // When the add expense button is clicked, open the bottom sheet
        val addExpenseButton: Button = findViewById(R.id.button_add_expense)
        addExpenseButton.setOnClickListener {
            AddExpenseBottomSheet(db, currentMonth).apply {
                show(supportFragmentManager, tag)
            }
        }

        // Open the prevision activity when clicking on the "make previsions" button
        val makePrevisionButton: Button = findViewById(R.id.button_make_previsions)
        makePrevisionButton.setOnClickListener {
            openPrevisions()
        }

        // Initialize the main RecylerView
        expensesRecyclerView = findViewById(R.id.expenses_recy_view)
        expensesRecyclerView.layoutManager = LinearLayoutManager(applicationContext)

        // When clicking on the history button, open the history activity
        val historyButton: Button = findViewById(R.id.button_history)
        historyButton.setOnClickListener {
            val intentPrevisionActivity = Intent(this, HistoryActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, account.uid)
            }
            startActivity(intentPrevisionActivity)
        }
    }

    /**
     * Load the account from the database and set the current month
     * to the last month that already had previsions
     */
    private suspend fun loadAccount() {
        account = db.accountDao().getAccountById(accountUid!!)
        title = getString(R.string.expenses_title, account.name)

        // Load the latest month
        // (not always matching the current month, switching to the next month
        // should be a manual action triggered by the user)

        val makePrevisionsButton: Button = findViewById(R.id.button_make_previsions)

        val monthsForCurrentAccount = db.monthDao().getMonthsForAccountUid(accountUid!!)

        // Open the previsions activity if no prevision has ever been done
        if (monthsForCurrentAccount.isEmpty()) {
            //openPrevisions()
            supportActionBar?.subtitle = getString(R.string.account_just_created_subtitle)
        } else {
            currentMonth = monthsForCurrentAccount.last()
            findViewById<View>(R.id.button_add_expense).visibility = View.VISIBLE

            val monthAsLocalDate: LocalDate =
                LocalDate.of(currentMonth.yearNumber, currentMonth.monthNumber, currentMonth.payday)
            val monthFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
            val formattedMonth: String = monthAsLocalDate.format(monthFormat)
            supportActionBar?.subtitle = getString(R.string.expenses_subtitle_month, formattedMonth)

            loadExpenses()

            // Check if the month has passed and the user can make a new prevision
            val now: LocalDate = LocalDate.now()
            if (now.isBefore(monthAsLocalDate)) {// If the month has non ended yet
                makePrevisionsButton.visibility = View.GONE //TODO check if total expenses reached 0
            }

        }
    }

    /**
     * Load all the expenses and their categories from the databse, display them in a recyclerview
     */
    suspend fun loadExpenses() {
        expensesRecyclerView.adapter = CategoryAdapter(
            db.categoryDao().getCategoriesForMonth(
                currentMonth.monthNumber,
                currentMonth.yearNumber,
                account.uid
            ), this, true, db, currentMonth.monthNumber, currentMonth.yearNumber
        )
    }

    /**
     * Open the previsions activity and pass the account id as extra
     */
    private fun openPrevisions() {
        val intentPrevisionActivity = Intent(this, PrevisionActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, account.uid)
        }
        startActivity(intentPrevisionActivity)
    }

    /**
     * When the user goes back to the already created activity, reload expenses from account
     */
    override fun onRestart() {
        super.onRestart()
        lifecycleScope.launch {
            loadAccount()
        }
    }
}