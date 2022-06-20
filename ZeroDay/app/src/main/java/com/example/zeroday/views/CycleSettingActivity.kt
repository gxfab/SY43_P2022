package com.example.zeroday.views

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.zeroday.R
import java.text.SimpleDateFormat
import java.util.*

class CycleSettingActivity : AppCompatActivity() {

    val cycles = arrayOf("Yearly", "Monthly", "Semi-Monthly", "Weekly")
    var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle_setting)
        val name = getIntent().getStringExtra("userName")

        val cycleSpinner = findViewById<Spinner>(R.id.cycle_setting_cycle)
        val cycleArray = ArrayAdapter(this, android.R.layout.simple_spinner_item, cycles)


        cycleArray.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        cycleSpinner.adapter = cycleArray


        val startingDate = findViewById<EditText>(R.id.cycle_setting_start_date)
        val dateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                updateEditTextDate()
            }
        }

        //Affichage date picker
        startingDate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View)
            {
                DatePickerDialog(this@CycleSettingActivity, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        findViewById<Button>(R.id.cycle_setting_continue_button).setOnClickListener()
        {
            val intent = Intent(this,StartTotalIncomesActivity::class.java)
            startActivity(intent)
        }
    }

    //Fonction permettant de mettre à jour l'edittext en fonction de la date selectionnée dans le date picker
    private fun updateEditTextDate()
    {
        val myFormat = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.UK)
        val startingDate = findViewById<EditText>(R.id.cycle_setting_start_date)
        startingDate.setText(dateFormat.format(calendar.time))
    }
}