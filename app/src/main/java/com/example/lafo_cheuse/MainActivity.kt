package com.example.lafo_cheuse

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.models.Category
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.*


const val EXTRA_MESSAGE = "com.exemple.test.MESSAGE"

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {

            val db = Room.databaseBuilder(
                applicationContext,
                LafoCheuseDatabase::class.java, "BDD Lafo-Cheuse"
            ).build()
            launch(Dispatchers.IO) {
                val catDao = db.categoryDao()
                catDao!!.createCategory(Category("Courses", "\uD83D\uDED2"))
                catDao.createCategory(Category("Bourses", "\uD83D\uDCB0"))
            }


            val chart = findViewById<PieChart>(R.id.piechart)

            val entries = ArrayList<PieEntry>()

            for (i in 0..3) {
                entries.add(PieEntry((Math.random() * 70 + 30).toFloat(), "Quarter " + (i + 1)))
            }
            val dataSet = PieDataSet(entries, "")

            dataSet.colors = ColorTemplate.PASTEL_COLORS.toList()
            dataSet.valueTextColor = Color.WHITE
            dataSet.valueTextSize = 12f

            val data = PieData(dataSet)

            chart.data = data
            val description = Description()
            description.textColor = Color.RED
            description.text = "Bootleg graph"
            chart.description = description
            chart.animateX(2000)
        }
    }
    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

}