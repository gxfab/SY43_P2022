package com.sy43_p22.piggy_bank.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sy43_p22.piggy_bank.MainActivity
import com.sy43_p22.piggy_bank.R
import com.sy43_p22.piggy_bank.adapter.ButtonAdapter
import com.sy43_p22.piggy_bank.database.PiggyBankDatabase
import com.sy43_p22.piggy_bank.database.entities.Category
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @description Parent class of SavingGoalsFragment & SpendingFragment
 * @param pageName Title displayed at the top of the fragment
 * @param layoutVertical Layout Id that should be used for sub-categories
 * @param layoutVerticalColor Same as Layout Id but with "gray" or "white"
 * @param layout Main layout Id
 */
open class CategoryFragment(
    private val pageName: String,
    private val layoutVertical: Int,
    private val layoutVerticalColor: String,
    private val layout: Int
) : Fragment(), MainActivity.IOnBackPressed {

    // database access
    private lateinit var db: PiggyBankDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // get the view
        val view = inflater.inflate(layout, container, false)

        // fetch database
        db = PiggyBankDatabase.getDatabase(view.context)

        // set page title name
        val title: TextView = view.findViewById(R.id.page_title)
        title.text = pageName

        return view
    }

    // what should happens when the back button is pressed
    // TODO : Not fully implemented
    override fun onBackPressed(): Boolean {
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    /**
     * @description Once the view is created, fetch the DB & set the adapter
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)

        MainScope().launch {
            val categories: List<Category> = db.piggyBankDAO().getAllCategories()

            verticalRecyclerView.adapter = ButtonAdapter(
                layoutVertical,
                categories,
                ButtonAdapter.OnClickListener(verticalRecyclerView, layoutVerticalColor),
                layoutVerticalColor,
            )

            // tell the recyclerview that the adapter changed
            verticalRecyclerView.adapter?.notifyDataSetChanged()
        }
    }
}