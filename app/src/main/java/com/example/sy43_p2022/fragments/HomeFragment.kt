package com.example.sy43_p2022.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.sy43_p2022.R
import com.example.sy43_p2022.database.PiggyBankDatabase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HomeFragment: Fragment(){
    lateinit var db: PiggyBankDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val button = inflater.inflate(R.layout.fragment_home, container, false)

        db = PiggyBankDatabase.getDatabase(button.context);

        val totalAmount = button.findViewById<TextView>(R.id.home_page_balance_amount)
        val spendingAmount = button.findViewById<TextView>(R.id.home_page_spending_amount)
        val savingAmount = button.findViewById<TextView>(R.id.home_page_saving_amount)
        val progressBar = button.findViewById<ProgressBar>(R.id.home_page_spending_progress)

        // fetch amount values
        MainScope().launch {
            val spending: Int = db.piggyBankDAO().getSpendingAmount()
            val saving: Int = db.piggyBankDAO().getSavingAmount()

            totalAmount.text = button.context.getString(R.string.amount, (saving - spending))
            spendingAmount.text = button.context.getString(R.string.amount, spending)
            savingAmount.text = button.context.getString(R.string.amount, saving)

            progressBar.progress = (spending * 100) / saving
        }


        // Construct "spending" Home Fragment button
        val spendingBtn = button.findViewById<Button>(R.id.home_fragment_button_spending)
        spendingBtn.setOnClickListener {
            val transaction = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, SpendingFragment())
            transaction.addToBackStack("spending")
            transaction.commit()
        }

        // Construct "saving" Home Fragment button
        val clickSavingGoals = button.findViewById<Button>(R.id.home_fragment_button_saving_goals)
        clickSavingGoals.setOnClickListener {
            val transaction = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, SavingGoalsFragments())
            transaction.addToBackStack("saving")
            transaction.commit()
        }

        return button
    }
}