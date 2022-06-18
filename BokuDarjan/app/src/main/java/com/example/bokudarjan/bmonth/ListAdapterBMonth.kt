package com.example.bokudarjan.bmonth

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.bokudarjan.ExpensesFragment
import com.example.bokudarjan.R
import com.example.bokudarjan.category.CategoryViewModel
import com.example.bokudarjan.envelope.EnvelopeViewModel
import com.example.bokudarjan.expense.Expense
import com.example.bokudarjan.expense.ExpenseViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.expense_card.view.*
import kotlinx.android.synthetic.main.expense_category.view.*
import kotlinx.android.synthetic.main.fragment_month.view.*


/**
 * ListAdapter of the [BMonth], allowing compatibility with recyclerViews.
 * Display months horizontally in the sidenav
 */
class ListAdapterBMonth: RecyclerView.Adapter<ListAdapterBMonth.MyViewHolder>() {

    private var monthList = emptyList<BMonth>()
    private lateinit var envelopeViewModel: EnvelopeViewModel
    private lateinit var expenseViewModel: ExpenseViewModel


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    /**
     * Inflate the fragment_month layout in the recyclerView and returns the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        envelopeViewModel = ViewModelProvider(parent.findViewTreeViewModelStoreOwner()!!).get(EnvelopeViewModel::class.java)
        expenseViewModel = ViewModelProvider(parent.findViewTreeViewModelStoreOwner()!!).get(ExpenseViewModel::class.java)

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_month, parent, false))
    }

    override fun getItemCount(): Int {
        return monthList.size
    }

    @SuppressLint("SetTextI18n")
    /**
     * Setup the content of the previously inflated view to reflect an entry in the list
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Log.d("ListAdapterBMonth","entering listadapter BMonth")

        var currentItem = monthList[position]
        holder.itemView.txtMonth.text = "Mois n°" + currentItem.id.toString()

        val month = holder.itemView.context.getSharedPreferences("pref",Context.MODE_PRIVATE).getInt("month", -1)
        if(currentItem.id == month){
            holder.itemView.imageView4.setColorFilter(Color.WHITE)
        }else{
            holder.itemView.imageView4.setColorFilter(Color.TRANSPARENT)
        }

        var sumAmount : Float= 0f

        //Display money sumAmount remaining to balance
        envelopeViewModel.getSumOfEnvelopes(currentItem.id).observeForever {
            if(it !=null)
            {
                sumAmount += it
                Log.d("ListAdapterBMonth","sumAmount envelope : "+sumAmount + " for month " + currentItem.id)
            }

            expenseViewModel.getSumOfNegativeExpenses(currentItem.id).observeForever {
                holder.itemView.monthSumValue.text = "?€"

                if(it !=null)
                {
                    sumAmount -= it
                    holder.itemView.monthSumValue.text = sumAmount.toString() + "€"
                    Log.d("ListAdapterBMonth","sumAmount expense: "+sumAmount + " for month " + currentItem.id)
                }

            }

        }


        Log.d("ListAdapterBMonth","Current month : "+ currentItem.id)


        holder.itemView.imageView4.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Changement de mois", Toast.LENGTH_SHORT).show()
            holder.itemView.context.getSharedPreferences("pref",Context.MODE_PRIVATE).edit().putInt("month", currentItem.id).apply()
            notifyDataSetChanged()
            var hostFragement = holder.itemView.rootView.findViewById<FragmentContainerView>(R.id.nav_host_fragment)[0]

            if (hostFragement.findViewById<RecyclerView>(R.id.recyclerViewCategory) != null){
                hostFragement.findViewById<RecyclerView>(R.id.recyclerViewCategory).adapter!!.notifyDataSetChanged()
            }
            if (hostFragement.findViewById<RecyclerView>(R.id.recyclerViewExpense) != null){


            }



        }

    }

    /**
     * Set up the list of [BMonth] that will be use to generate the recyclerView
     */
    fun setData(month: List<BMonth>){
        this.monthList = month
        notifyDataSetChanged()
    }

}