package com.sy43_p22.piggy_bank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.sy43_p22.piggy_bank.R
import com.sy43_p22.piggy_bank.database.PiggyBankDatabase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @description Home page
 */
class HomeFragment: Fragment(){
    // database access
    lateinit var db: PiggyBankDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // get the view
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // fetch the database
        db = PiggyBankDatabase.getDatabase(view.context);

        // fetch texts values to be edited
        val totalAmount = view.findViewById<TextView>(R.id.home_page_balance_amount)
        val spendingAmount = view.findViewById<TextView>(R.id.home_page_spending_amount)
        val savingAmount = view.findViewById<TextView>(R.id.home_page_saving_amount)
        val progressBar = view.findViewById<ProgressBar>(R.id.home_page_spending_progress)

        // fetch amount values
        MainScope().launch {
            var spending: Int? = db.piggyBankDAO().getSpendingAmount()
            var saving: Int? = db.piggyBankDAO().getSavingAmount()

            // null on first iteration
            if (spending == null || saving == null) {
                spending = 0
                saving = 0
            }

            // update text values
            totalAmount.text = view.context.getString(R.string.amount, (saving - spending))
            spendingAmount.text = view.context.getString(R.string.amount, spending)
            savingAmount.text = view.context.getString(R.string.amount, saving)

            // update the progress bar
            progressBar.progress = if (saving > 0) (spending * 100) / saving else 0
        }


        // Construct "spending" Home Fragment button
        view.findViewById<Button>(R.id.home_fragment_button_spending)
            .setOnClickListener {
                val transaction = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, SpendingFragment())
                transaction.addToBackStack("spending")
                transaction.commit()
            }

        // Construct "saving" Home Fragment button
        view.findViewById<Button>(R.id.home_fragment_button_saving_goals)
            .setOnClickListener {
                val transaction = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, SavingGoalsFragments())
                transaction.addToBackStack("saving")
                transaction.commit()
            }

        return view
    }
}