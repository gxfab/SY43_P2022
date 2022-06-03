package net.yolopix.moneyz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Month
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PrevisionActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prevision)

        // Make previsions for the current month
        val now = LocalDate.now()

        val monthNumber = now.monthValue
        val yearNumber = now.year

        db = DatabaseFactory.getDB(applicationContext)

        // Get account passed as extra message
        val accountUid: Int? = intent.getStringExtra(EXTRA_MESSAGE)?.toInt()
        lifecycleScope.launch {
            if (accountUid == null) {
                finish()
            } else {
                //loadAccount(accountUid)
            }
        }

        val addCategoryButton = findViewById<Button>(R.id.button_add_category)
        addCategoryButton.setOnClickListener {
            AddCategoryBottomSheet(db, monthNumber, yearNumber).apply {
                show(supportFragmentManager, tag)
            }

        }

        recyclerView = findViewById(R.id.category_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        lifecycleScope.launch {
            loadCategory(monthNumber, yearNumber)
        }

        // When the user has finished to make previsions
        val doneButton: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton =
            findViewById(R.id.button_done)
        doneButton.setOnClickListener {
            val editTextSalary: EditText = findViewById(R.id.edittext_salary)
            val editTextPayday: EditText = findViewById(R.id.editetext_payday)
            val newMonth = Month(
                now.monthValue,
                now.year,
                editTextSalary.text.toString().toDouble(),
                editTextPayday.text.toString().toInt(),
                accountUid!!
            )
            lifecycleScope.launch {
                db.monthDao().insertMonth(newMonth)
            }
            finish()
        }

        setTitle(R.string.previsions_title)
        val monthFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        val formattedMonth: String = now.format(monthFormat)
        supportActionBar?.subtitle = "Pour le mois de $formattedMonth"

    }

    suspend fun loadCategory(monthNumber: Int, yearNumber: Int) {
        val adapter =
            CategoryAdapter(db.categoryDao().getCategoriesForMonth(monthNumber, yearNumber))
        recyclerView.adapter = adapter
    }
}