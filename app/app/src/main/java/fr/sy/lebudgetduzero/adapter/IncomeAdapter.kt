package fr.sy.lebudgetduzero.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.sy.lebudgetduzero.item.IncomeItem
import fr.sy.lebudgetduzero.MainActivity
import fr.sy.lebudgetduzero.R
import java.text.SimpleDateFormat

class IncomeAdapter (
    private val context: MainActivity,
    private val incomeList:List<IncomeItem>,
    private val layoutId: Int
): RecyclerView.Adapter<IncomeAdapter.ViewHolder>(){

    //Boite pour ranger tous les composants à controler
    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        //Nom de l'income
        val incomeTitle = view.findViewById<TextView>(R.id.nameText)
        val incomeDate = view.findViewById<TextView>(R.id.dateText)
        val incomeValue = view.findViewById<TextView>(R.id.priceText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Recuperation info de la liste
        val currentIncome=incomeList[position]

        //Conversion de la date
        val date= SimpleDateFormat("dd-MM-yyyy").format(currentIncome.date.toLong() * 1000L)

        holder.incomeTitle.text=currentIncome.name
        holder.incomeDate.text= date
        holder.incomeValue.text=currentIncome.value.toString()
    }

    override fun getItemCount(): Int {
        return incomeList.size
    }


}