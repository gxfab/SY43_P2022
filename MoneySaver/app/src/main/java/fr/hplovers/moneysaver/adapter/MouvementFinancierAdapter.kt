package fr.hplovers.moneysaver.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.hplovers.moneysaver.MainActivity
import fr.hplovers.moneysaver.MouvementFinancier
import fr.hplovers.moneysaver.R

class MouvementFinancierAdapter (
    private val context: MainActivity,
    private val mouvementList: List<MouvementFinancier>,
    private val layoutId: Int
        ): RecyclerView.Adapter<MouvementFinancierAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val mouvementDescr:TextView? = view.findViewById(R.id.description)
        val mouvementMontant:TextView? = view.findViewById(R.id.montant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical_depenses, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //récupérer les infos du mouvement financier
        val currentMouvementFinancier = mouvementList[position]

        //Mise à jour des données du mouvement
        holder.mouvementDescr?.text = currentMouvementFinancier.description
        holder.mouvementMontant?.text = currentMouvementFinancier.montant.toString()
    }

    override fun getItemCount(): Int = mouvementList.size
}