package com.example.gestimali.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gestimali.NewEnvelopeFragment
import com.example.gestimali.NewFixedExpenseFragment
import com.example.gestimali.NewIncomeFragment
import com.example.gestimali.R
import com.example.gestimali.envelope.EnvelopeViewModel
import com.example.gestimali.fixexpense.FixedExpenseViewModel
import com.example.gestimali.income.IncomeViewModel
import com.example.gestimali.wish.WishViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.min

/**
 * Class used for the recycler view in the AddBudgetActivity.
 * It will display the correct category (i.e. income, expense,...) and the money flow associated of that category
 *
 * @param intMonth: It corresponds to the integer associated to the current (1 = january, 2 = february,...)
 * @param activity: A reference to the activity that use this recycler view
 */
internal class CategoryAdapter(var intMonth: Int, var activity: AppCompatActivity ) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    private lateinit var  mIncomeViewModel : IncomeViewModel
    private lateinit var  mFixedExpenseViewModel : FixedExpenseViewModel
    private lateinit var  mWishViewModel : WishViewModel
    private lateinit var  mEnvelopeViewModel : EnvelopeViewModel

    /**
     * List of the different categories
     */
    private var categoryList = listOf("Income", "Expense", "Wish/Saving", "Envelope")

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val categoryTitle = itemView.findViewById<TextView>(R.id.category_name)
        val floatingButton = itemView.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val moneyFlowRecyclerView = itemView.findViewById<RecyclerView>(R.id.money_flow_recycler)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_plan_category,parent,false)


        mIncomeViewModel = ViewModelProvider(activity).get(IncomeViewModel::class.java)
        mFixedExpenseViewModel = ViewModelProvider(activity).get(FixedExpenseViewModel::class.java)
        mWishViewModel = ViewModelProvider(activity).get(WishViewModel::class.java)
        mEnvelopeViewModel = ViewModelProvider(activity).get(EnvelopeViewModel::class.java)


        return CategoryAdapter.ViewHolder(view)
    }


    /**
     * Set the corresponding function for the add buttons and add the correct adapter for the recycler view of money flow
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryTitle.text = categoryList[position]

        if(position==0){
            holder.floatingButton.setOnClickListener{onClickIncome(holder.itemView)}
        }
        else if(position == 1 ){
            holder.floatingButton.setOnClickListener{onClickExpense(holder.itemView)}
        }
        else if (position == 3){
            holder.floatingButton.setOnClickListener{onClickEnvelope(holder.itemView)}
        }
        val moneyFlowAdapter = MoneyFlowAdapter(position)

        mIncomeViewModel.readAllData.observe(activity,Observer{income ->
            moneyFlowAdapter.setData(income)
        })
        mFixedExpenseViewModel.readAllData.observe(activity,Observer{expense ->
            moneyFlowAdapter.setData(expense)
        })
        mWishViewModel.readAllData.observe(activity,Observer{wish ->
            moneyFlowAdapter.setData(wish)
        })
        mEnvelopeViewModel.readAllData.observe(activity,Observer{envelope ->
            moneyFlowAdapter.setData(envelope)
        })
        holder.moneyFlowRecyclerView.adapter = moneyFlowAdapter
    }


    /**
     * @return Integer : The number of category to display (income, expense, wish, envelope)
     */
    override fun getItemCount(): Int = 4


    private fun onClickIncome(p0: View?) {
        val fragment: NewIncomeFragment? =
            NewIncomeFragment.newInstance(intMonth)
        val fm: FragmentManager = (activity).supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack("addIncomeFragment")
        if (fragment != null) {
            ft.replace(R.id.popup_fragment, fragment)
        }
        ft.commit()
    }

    private fun onClickExpense(p0: View?) {
        val fragment: NewFixedExpenseFragment? =
            NewFixedExpenseFragment.newInstance(intMonth)
        val fm: FragmentManager = (activity).supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack("addExpenseFragment")
        if (fragment != null) {
            ft.replace(R.id.popup_fragment, fragment)
        }
        ft.commit()
    }


    private fun onClickEnvelope(p0: View?) {
        val fragment: NewEnvelopeFragment? =
            NewEnvelopeFragment.newInstance(intMonth)
        val fm: FragmentManager = (activity).supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.addToBackStack("addIncomeFragment")
        if (fragment != null) {
            ft.replace(R.id.popup_fragment, fragment)
        }
        ft.commit()
    }

}
