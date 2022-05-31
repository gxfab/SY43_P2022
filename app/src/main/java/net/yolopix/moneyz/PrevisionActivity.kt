package net.yolopix.moneyz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import net.yolopix.moneyz.model.AppDatabase
import net.yolopix.moneyz.model.entities.Category

class PrevisionActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_prevision)
        var slider = findViewById<com.google.android.material.slider.Slider>(R.id.salary_slider)
        var text_salary = findViewById<TextView>(R.id.salary_text);

        slider.addOnChangeListener { slider, value, fromUser ->
            text_salary.text = String.format("%.2f", value.toDouble())
        }



        db = DatabaseFactory.getDB(applicationContext)
        val addCategoryButton = findViewById<Button>(R.id.button_add_category)
        addCategoryButton.setOnClickListener {
            AddCategoryBottomSheet(db).apply {
                show(supportFragmentManager, tag)
            }

        }

        recyclerView = findViewById(R.id.category_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        lifecycleScope.launch {
            loadCategory()
        }


    }
     fun deleteCategory(category: Category){
        lifecycleScope.launch{
            db.categoryDao().deleteCategory(category)
        }

    }


    suspend fun loadCategory() {
        val adapter = CategoryAdapter(db.categoryDao().getAll() as MutableList<Category>)
        recyclerView.adapter = adapter
    }
}