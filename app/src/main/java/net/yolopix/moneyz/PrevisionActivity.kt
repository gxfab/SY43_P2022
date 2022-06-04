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

    /** An object representing the time when the activity has been opened */
    private lateinit var now: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prevision)

        // Get account passed as extra message
        val accountUid: Int? = intent.getStringExtra(EXTRA_MESSAGE)?.toInt()
        lifecycleScope.launch {
            if (accountUid == null) {
                finish()
            } else {
                //loadAccount(accountUid)
            }
        }

        progressTextView = findViewById(R.id.progress_text)
        salaryEditText = findViewById(R.id.edittext_salary)
        salarySlider = findViewById(R.id.slider_salary)

        // Make previsions for the current month
        now = LocalDate.now()

        db = DatabaseFactory.getDB(applicationContext)

        // Load the bar when the activity is created
        lifecycleScope.launch {
            loadBudgetBar()
        }

        // Refresh the bar when the salary was changed
        salaryEditText.addTextChangedListener {
            lifecycleScope.launch {
                loadBudgetBar()
            }
        }

        // Open the bottom sheet to add a new category when clicking on the "add category" button
        val addCategoryButton = findViewById<Button>(R.id.button_add_category)
        addCategoryButton.setOnClickListener {
            AddCategoryBottomSheet(db, now.monthValue, now.year).apply {
                show(supportFragmentManager, tag)
            }

        }

        // Initialize the category RecyclerView
        recyclerView = findViewById(R.id.category_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        lifecycleScope.launch {
            loadCategories()
        }

        // When the user has finished to make previsions
        val doneButton: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton =
            findViewById(R.id.button_done)
        doneButton.setOnClickListener {
            val paydayEditText: EditText = findViewById(R.id.editetext_payday)
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
        supportActionBar?.subtitle = "Pour le mois de $formattedMonth"

    }

    /**
     * Load all categories from the current prevision from the database
     * and display the recyclerview containing these categories
     */
    suspend fun loadCategories() {
        val adapter =
            CategoryAdapter(db.categoryDao().getCategoriesForMonth(now.monthValue, now.year))
        recyclerView.adapter = adapter
    }

    /**
     * Loads the data from the current prevision
     * and refresh the progress bar at the top of the screen
     */
    private suspend fun loadBudgetBar() {
        val categories = db.categoryDao().getCategoriesForMonth(now.monthValue, now.year)
        var categorizedAmount = 0f

        for (category in categories) {
            categorizedAmount += category.predictedAmount
        }
        val totalAmount: Float = salaryEditText.text.toString().toFloat()

        progressTextView.text = getString(
            R.string.prevision_progress_format,
            categorizedAmount.toString(),
            totalAmount.toString()
        )

        salarySlider.value = categorizedAmount
        salarySlider.valueTo = totalAmount
    }

}