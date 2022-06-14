package net.yolopix.moneyz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.slider.Slider
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Month
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * This activity enables the user to make a new prevision for the current month.
 * Informations such as the salary and the payday can be filled in here
 * Each category can be independently budgeted
 */
class PrevisionActivity : AppCompatActivity() {

    /** Database */
    private lateinit var db: AppDatabase

    /** The database identifier of the loaded account, null if no account has been loaded yet */
    private var accountUid: Int? = null

    /** An object representing the time when the activity has been opened */
    private lateinit var now: LocalDate

    // Widgets
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressTextView: TextView
    private lateinit var salaryEditText: EditText
    private lateinit var salarySlider: Slider
    private lateinit var doneFloatingActionButton: ExtendedFloatingActionButton
    private lateinit var salaryTextField: com.google.android.material.textfield.TextInputLayout
    private lateinit var paydayTextField: com.google.android.material.textfield.TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prevision)

        // Get account passed as extra message
        accountUid = intent.getIntExtra(EXTRA_MESSAGE, 0)
        if (accountUid == null) {
            finish()
        }

        db = DatabaseFactory.getDB(applicationContext)
        recyclerView = findViewById(R.id.category_recycler_view)
        progressTextView = findViewById(R.id.progress_text)
        salaryEditText = findViewById(R.id.edittext_salary)
        salarySlider = findViewById(R.id.slider_salary)
        now = LocalDate.now()
        doneFloatingActionButton = findViewById(R.id.button_done)
        salaryTextField = findViewById(R.id.textfield_salary)
        paydayTextField = findViewById(R.id.textfield_payday)

        // Set salary value
        lifecycleScope.launch {
            val categorizedAmount = calculateCategorizedAmount()
            if (categorizedAmount > 0f) salaryEditText.setText(categorizedAmount.toString())
        }

        // When the salary value was changed
        salaryEditText.addTextChangedListener {
            lifecycleScope.launch {
                // Refresh the bar
                loadBudgetBar()

                // Show an error if the salary value is less than the previsions
                val salaryValue = it.toString().toFloatOrNull()

                salaryTextField.error = when {
                    salaryValue == null -> getString(R.string.error_empty_text)
                    salaryValue < calculateCategorizedAmount() -> getString(R.string.error_salary_greater_than_previsions)
                    else -> null
                }

                checkFormErrors()
            }
        }

        // Restrict day of month input to proper values
        val paydayEditText: EditText = findViewById(R.id.edittext_payday)
        paydayEditText.filters = arrayOf(NumberMaxInputFilter(now.lengthOfMonth()))
        paydayEditText.addTextChangedListener {
            val paydayValue = it.toString().toIntOrNull()

            paydayTextField.error = when (paydayValue) {
                null -> getString(R.string.error_empty_text)
                in 1..now.lengthOfMonth() -> null
                else -> getString(R.string.error_invalid_day_of_month)
            }
            checkFormErrors()
        }

        // Open the bottom sheet to add a new category when clicking on the "add category" button
        val addCategoryButton = findViewById<Button>(R.id.button_add_category)
        addCategoryButton.setOnClickListener {
            AddCategoryBottomSheet(
                db,
                now.monthValue,
                now.year,
                accountUid!!,
                salaryEditText.text.toString().toFloatOrNull()
            ).apply {
                show(supportFragmentManager, tag)
            }

        }

        // Initialize the category RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        lifecycleScope.launch {

            val swipeToDeleteExpense = object : SwipeToDeleteExpense(){
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    lifecycleScope.launch {  val categoryList = db.categoryDao().getCategoriesForMonth(now.monthValue, now.year, accountUid!!)
                        val position = viewHolder.adapterPosition



                        db.categoryDao().deleteCategory(categoryList[position])
                        loadAll()
                    }


                }

            }
            val itemTouchHelper = ItemTouchHelper(swipeToDeleteExpense)
            itemTouchHelper.attachToRecyclerView(recyclerView)
        }








        // Load asynchronous databases operations when the activity is created
        loadAll()

        // When the user has finished to make previsions
        doneFloatingActionButton.setOnClickListener {
            val newMonth = Month(
                now.monthValue,
                now.year,
                salaryEditText.text.toString().toDouble(),
                paydayEditText.text.toString().toInt(),
                accountUid!!
            )
            lifecycleScope.launch {
                db.monthDao().insertMonth(newMonth)
            }
            finish()
        }

        // Display the current month in the action bar
        setTitle(R.string.previsions_title)
        val monthFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        val formattedMonth: String = now.format(monthFormat)
        supportActionBar?.subtitle = getString(R.string.prevision_subtitle_month, formattedMonth)

    }

    /**
     * Load categories, limits and budget bar
     */
    fun loadAll() {
        lifecycleScope.launch {
            loadCategories()
            loadBudgetBar()
            loadLimits()
        }
    }

    /**
     * Load all categories from the current prevision from the database
     * and display the recyclerview containing these categories
     */
    private suspend fun loadCategories() {
        val adapter =
            CategoryAdapter(
                db.categoryDao().getCategoriesForMonth(now.monthValue, now.year, accountUid!!),
                this,
                false,
                db,
            )
        recyclerView.adapter = adapter
    }

    /**
     * Load and apply min and max values for EditText
     */
    private suspend fun loadLimits() {
        val categorizedAmount: Float = calculateCategorizedAmount()

        if (salaryEditText.text.toString().toFloat() < categorizedAmount) {
            salaryEditText.setText(categorizedAmount.toString())
        }
    }

    /**
     * Loads the data from the current prevision
     * and refresh the progress bar at the top of the screen
     */
    private suspend fun loadBudgetBar() {

        val categorizedAmount = calculateCategorizedAmount()
        var totalAmount: Float? = salaryEditText.text.toString().toFloatOrNull()

        if (totalAmount == null)
            totalAmount = 0f

        progressTextView.text = getString(
            R.string.prevision_progress_format,
            String.format("%.2f", categorizedAmount),
            String.format("%.2f", totalAmount)
        )

        if (totalAmount >= categorizedAmount)
            salarySlider.valueTo = java.lang.Float.max(totalAmount, 1f)

        if (categorizedAmount in 0f..totalAmount)
            salarySlider.value = categorizedAmount
        else
            salarySlider.value = 0f
    }

    /**
     * Calculate the total amount of categorized money
     * @return total amount of all previsions from the categories
     */
    private suspend fun calculateCategorizedAmount(): Float {
        val categories =
            db.categoryDao().getCategoriesForMonth(now.monthValue, now.year, accountUid!!)
        var categorizedAmount = 0f

        for (category in categories) {
            categorizedAmount += category.predictedAmount
        }
        return categorizedAmount
    }

    /**
     * Checks if any of the TextEdits contains an error
     * If so, disable the submit button
     */
    private fun checkFormErrors() {
        doneFloatingActionButton.isEnabled =
            salaryTextField.error == null && paydayTextField.error == null
    }

}