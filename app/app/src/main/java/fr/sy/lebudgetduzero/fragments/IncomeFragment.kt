package fr.sy.lebudgetduzero.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.sy.lebudgetduzero.IncomeItem
import fr.sy.lebudgetduzero.MainActivity
import fr.sy.lebudgetduzero.R
import fr.sy.lebudgetduzero.adapter.IncomeAdapter

class IncomeFragment(private val context:MainActivity): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_income, container, false)

        //Creation de la liste de plante
        val incomeList= arrayListOf<IncomeItem>()
        //Premiere plante de la liste
        incomeList.add(IncomeItem(1,"Paypal photo", 75.32F,"04/06/2022"))
        incomeList.add(IncomeItem(1,"Salaire", 1328.6F,"04/06/2022"))
        incomeList.add(IncomeItem(1,"APL", 28.68F,"15/05/2022"))
        incomeList.add(IncomeItem(1,"Remboursement BDS", 95.62F,"12/05/2022"))



        //Recuperation du recycler view
        val recyclerView= view.findViewById<RecyclerView>(R.id.recylcerIncome)
        recyclerView.adapter = IncomeAdapter(context, incomeList, R.layout.item_activity)

        return view
    }
}