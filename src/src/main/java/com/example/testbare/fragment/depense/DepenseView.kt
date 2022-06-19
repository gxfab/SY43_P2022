package com.example.testbare.fragment.depense

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testbare.database.AppDatabase
import com.example.testbare.database.entities.Depense
import com.example.testbare.R
import java.text.SimpleDateFormat

class DepenseView(val listDepenses: ArrayList<Depense>)
: RecyclerView.Adapter<DepenseView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ma_ligne = LayoutInflater.from(parent.context).inflate(R.layout.item,parent, false)
        return ViewHolder(ma_ligne)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDepenses[position], listDepenses, this, position)
    }

    override fun getItemCount(): Int = listDepenses.size


    class ViewHolder(val elementDeListe : View) : RecyclerView.ViewHolder(elementDeListe) {
        fun bind(depense: Depense, listDepenses: ArrayList<Depense>, viewAdapter : DepenseView, position: Int) = with(itemView)
        {
            itemView.findViewById<TextView>(R.id.frgmItem_tv_categorie).text = depense.dep_categorie
            itemView.findViewById<TextView>(R.id.frgmItem_tv_montant).text = depense.dep_montant.toString() + " €"
            val date = SimpleDateFormat("dd-MM-yyyy").format(depense.dep_date)
            itemView.findViewById<TextView>(R.id.frgmItem_tv_date).text = date

            val lytBoutons = itemView.findViewById<LinearLayout>(R.id.frgmItem_lyt_boutons)
            lytBoutons.visibility = View.GONE
            itemView.findViewById<Button>(R.id.frgmItem_btn_item).setOnClickListener {
                if (lytBoutons.visibility == View.VISIBLE)
                    lytBoutons.visibility = View.GONE
                else
                    lytBoutons.visibility = View.VISIBLE
            }

            itemView.findViewById<ImageButton>(R.id.frgmItem_btn_supprimer).setOnClickListener {
                val depenseDao = AppDatabase.getDatabase(context).DepenseDao()
                depenseDao.deleteDepense(depense.dep_id)
                listDepenses.remove(depense)
                viewAdapter.notifyItemRemoved(position)
            }
        }
    }
}