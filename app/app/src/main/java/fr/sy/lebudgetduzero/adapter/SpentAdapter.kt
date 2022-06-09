package fr.sy.lebudgetduzero.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.sy.lebudgetduzero.MainActivity
import fr.sy.lebudgetduzero.R
import fr.sy.lebudgetduzero.item.SpentItem
import java.text.SimpleDateFormat

class SpentAdapter (
    private val context: MainActivity,
    private val spentList:List<SpentItem>,
    private val layoutId: Int
): RecyclerView.Adapter<SpentAdapter.ViewHolder>(){

    //Boite pour ranger tous les composants à controler
    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        //Nom de l'income
        val spentTitle = view.findViewById<TextView>(R.id.nameText)
        val spentDate = view.findViewById<TextView>(R.id.dateText)
        val spentValue = view.findViewById<TextView>(R.id.priceText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Recuperation info de la liste
        val currentSpent=spentList[position]

        //Conversion de la date
        val date= SimpleDateFormat("dd-MM-yyyy").format(currentSpent.date.toLong() * 1000L)

        holder.spentTitle.text=currentSpent.name
        holder.spentDate.text= date
        holder.spentValue.text=currentSpent.value.toString()
    }

    override fun getItemCount(): Int {
        return spentList.size
    }


}