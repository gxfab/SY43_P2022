package com.example.testbare.budget

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.testbare.R
import com.example.testbare.database.AppDatabase
import com.example.testbare.database.entities.Depense
import kotlinx.coroutines.launch

class ClasseTemplate : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_budget, container, false)

        val context : Context = this.context as Context
        val depenseDao = AppDatabase.getDatabase(context).DepenseDao()
        var depenses : List<Depense>
        lifecycleScope.launch {
            depenses = depenseDao.getAllDepenses()
            if(depenses.isEmpty())
                Toast.makeText(context,"Vous n'avez pas encore saisi de dépense",Toast.LENGTH_LONG).show()
            var recycler = view.findViewById<RecyclerView>(R.id.frgmBudget_rc_depenses)
            recycler.layoutManager = LinearLayoutManager(context)
            recycler.layoutManager = LinearLayoutManager(context)
            recycler.adapter = TemplateView(depenses.toTypedArray())
            {
                Toast.makeText(context,"Vous avez sélectionné " + it,Toast.LENGTH_SHORT ).show()
            }
        }

        return view
    }
}