package fr.sy.lebudgetduzero

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import fr.sy.lebudgetduzero.database.AppDatabase
import fr.sy.lebudgetduzero.item.IncomeItem
import fr.sy.lebudgetduzero.item.SpentItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS


class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    fun addTransaction(view: View){
        val editName=findViewById<EditText>(R.id.editNameItem).text.toString()
        val editValue=findViewById<EditText>(R.id.editValue).text.toString().toFloat()
        val editDate= (SimpleDateFormat("dd-MM-yyyy").parse(findViewById<EditText>(R.id.editDate).text.toString()).time/1000).toInt()
        val editType=Integer.parseInt(findViewById<EditText>(R.id.editType).text.toString())

        val isDepense=findViewById<RadioButton>(R.id.isDepense).isChecked

        val applicationScope = CoroutineScope(SupervisorJob())
        applicationScope.launch {

            //Db
            val db = AppDatabase.getDatabase(applicationContext)

            if(isDepense){
                db.spentDao().insertAll(SpentItem(name=editName,value=editValue,id_type=editType,date=editDate))
            }else{
                db.incomeDao().insertAll(IncomeItem(name=editName,value=editValue,date=editDate))
            }

            //Redirection
            val intent= Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun selectDate(view: View){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            findViewById<EditText>(R.id.editDate).setText("" + dayOfMonth + "-" + (monthOfYear+1) + "-" + year)

        }, year, month, day)

        dpd.show()
    }
}