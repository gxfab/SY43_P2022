package fr.hplovers.moneysaver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.hplovers.moneysaver.MainActivity
import fr.hplovers.moneysaver.MouvementFinancier
import fr.hplovers.moneysaver.MouvementFinancierRepo.Singleton.MouvementFinancierList
import fr.hplovers.moneysaver.R
import fr.hplovers.moneysaver.adapter.MouvementFinancierAdapter

class HomeFragment(
    private val context : MainActivity
) : Fragment() {

    //Injecte le layout fragment_home
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home,container,false)

        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter=MouvementFinancierAdapter(context, MouvementFinancierList, R.layout.item_vertical_depenses)

        return view
    }

}