package com.example.testbare.fragment.budget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.testbare.R
import com.example.testbare.database.AppDatabase
import com.example.testbare.database.entities.Budget

class BudgetView(val listBudgets: ArrayList<Budget>)
    : RecyclerView.Adapter<BudgetView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val ma_ligne = LayoutInflater.from(parent.context).inflate(R.layout.item,parent, false)
        return ViewHolder(ma_ligne)
    }

    override fun getItemCount(): Int = listBudgets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listBudgets[position], listBudgets, this, position)
    }

    class ViewHolder(val elementDeListe : View) : RecyclerView.ViewHolder(elementDeListe)
    {
        fun bind(budget: Budget, listBudgets : ArrayList<Budget>, viewAdapter : BudgetView, position : Int) = with(itemView)
        {
            itemView.findViewById<TextView>(R.id.frgmItem_tv_categorie).text = budget.bud_categorie
            itemView.findViewById<TextView>(R.id.frgmItem_tv_montant).text = budget.bud_montant.toString() + " €"

            val lytBoutons = itemView.findViewById<LinearLayout>(R.id.frgmItem_lyt_boutons)
            lytBoutons.visibility = View.GONE
            itemView.findViewById<Button>(R.id.frgmItem_btn_item).setOnClickListener {
                if (lytBoutons.visibility == View.VISIBLE)
                    lytBoutons.visibility = View.GONE
                else
                    lytBoutons.visibility = View.VISIBLE
            }
            itemView.findViewById<ImageButton>(R.id.frgmItem_btn_supprimer).setOnClickListener {
                val budgetDao = AppDatabase.getDatabase(context).BudgetDao()
                budgetDao.deleteBudget(budget.bud_id)
                listBudgets.remove(budget)
                viewAdapter.notifyItemRemoved(position)
            }
        }
    }
}