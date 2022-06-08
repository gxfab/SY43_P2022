package fr.sy.lebudgetduzero.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import fr.sy.lebudgetduzero.IncomeItem
import fr.sy.lebudgetduzero.MainActivity
import fr.sy.lebudgetduzero.R
import fr.sy.lebudgetduzero.adapter.IncomeAdapter
import fr.sy.lebudgetduzero.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class IncomeFragment(private val context:MainActivity): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_income, container, false)



        //Creation du lien à la base de données
        val applicationScope = CoroutineScope(SupervisorJob())

        //Recuperation du recycler view
        val recyclerView= view.findViewById<RecyclerView>(R.id.recylcerIncome)

        applicationScope.launch {
            val db=AppDatabase.getDatabase(context,applicationScope)
            //db.incomeDao().insertAll(IncomeItem(1,"Paypal photo", 75.32F,"04/06/2022"))
            db.incomeDao().insertAll(IncomeItem(2,"Paiement école", 610.98F,"02/06/2022"))

            val adapter=IncomeAdapter(context, db.incomeDao().getAll(), R.layout.item_activity)
            activity?.runOnUiThread{
                recyclerView.adapter = adapter
            }

        }

        return view
    }
}