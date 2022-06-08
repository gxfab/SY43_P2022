package net.yolopix.moneyz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.slider.Slider
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Month
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PrevisionActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressTextView: TextView
    private lateinit var salaryEditText: EditText
    private lateinit var salarySlider: Slider
    private var accountUid: Int? = null
    private lateinit var doneFloatingActionButton: ExtendedFloatingActionButton
    private lateinit var salaryTextField: com.google.android.material.textfield.TextInputLayout
    private lateinit var paydayTextField: com.google.android.material.textfield.TextInputLayout

    /** An object representing the time when the activity has been opened */
    private lateinit var now: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prevision)

        // Get account passed as extra message
        accountUid = intent.getStringExtra(EXTRA_MESSAGE)?.toInt()
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
        paydayEditText.filters = arrayOf(NumberRangeInputFilter(1, now.lengthOfMonth()))
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
            AddCategoryBottomSheet(db, now.monthValue, now.year, accountUid!!).apply {
                show(supportFragmentManager, tag)
            }

        }

        // Initialize the category RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

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
                db
            )
        recyclerView.adapter = adapter
    }

    /**
     * Load min and max values for EditText
     */
    private suspend fun loadLimits() {
        val categorizedAmount: Float = calculateCategorizedAmount()
        salaryEditText.filters = arrayOf(NumberRangeInputFilter(categorizedAmount, Float.MAX_VALUE))

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