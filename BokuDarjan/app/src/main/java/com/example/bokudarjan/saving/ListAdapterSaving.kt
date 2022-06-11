package com.example.bokudarjan.saving

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.EditSavingDialog
import com.example.bokudarjan.R
import com.example.bokudarjan.category.Category
import com.example.bokudarjan.category.ListAdapterCategory
import com.example.bokudarjan.envelope.EnvelopeViewModel
import kotlinx.android.synthetic.main.saving_card.view.*

class ListAdapterSaving : RecyclerView.Adapter<ListAdapterSaving.MyViewHolder>() {

    private var savingList = emptyList<Saving>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate( R.layout.saving_card , parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val manager: FragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager

        val currentItem = savingList[position]
        holder.itemView.savingName.text = currentItem.name
        holder.itemView.savingAmount.text = String.format("%.2f",currentItem.current) + "€ / " + String.format("%.2f",currentItem.to_reach) + "€"
        holder.itemView.savingProgress.max = currentItem.to_reach.toInt()
        holder.itemView.savingProgress.progress = currentItem.current.toInt()
        holder.itemView.card.setOnClickListener {
            var dialog = EditSavingDialog(currentItem.name, currentItem.current)
            dialog.show(manager, "");
        }
    }

    override fun getItemCount(): Int {
        return savingList.size
    }

    fun setData(saving: List<Saving>){
        this.savingList = saving
        notifyDataSetChanged()
    }
}