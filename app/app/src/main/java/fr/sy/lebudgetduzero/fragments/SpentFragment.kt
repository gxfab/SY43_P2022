package fr.sy.lebudgetduzero.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.sy.lebudgetduzero.MainActivity
import fr.sy.lebudgetduzero.R
import fr.sy.lebudgetduzero.adapter.SpentAdapter
import fr.sy.lebudgetduzero.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SpentFragment(private val context:MainActivity): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_spent, container, false)

        //Traitement base de données dans un autre thread
        val applicationScope = CoroutineScope(SupervisorJob())
        applicationScope.launch {
            val db=AppDatabase.getDatabase(context,applicationScope)

            val adapter= SpentAdapter(context, db.spentDao().getAll(), R.layout.item_activity)
            activity?.runOnUiThread{
                val recyclerView= view.findViewById<RecyclerView>(R.id.recylcerSpent)
                recyclerView.adapter = adapter
            }
        }

        return view
    }
}