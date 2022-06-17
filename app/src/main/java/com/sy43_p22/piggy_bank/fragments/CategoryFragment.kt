package com.sy43_p22.piggy_bank.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.sy43_p22.piggy_bank.MainActivity
import com.sy43_p22.piggy_bank.R
import com.sy43_p22.piggy_bank.adapter.ButtonAdapter
import com.sy43_p22.piggy_bank.database.PiggyBankDatabase
import com.sy43_p22.piggy_bank.database.entities.Category
import kotlinx.coroutines.launch

open class CategoryFragment(
    private val pageName: String,
    private val layoutVertical: Int,
    private val layoutVerticalColor: String,
    private val layout: Int
) : Fragment(), MainActivity.IOnBackPressed {
    private lateinit var db: PiggyBankDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout, container, false)

        db = PiggyBankDatabase.getDatabase(view.context)

        val title: TextView = view.findViewById(R.id.page_title)
        title.text = pageName

        return view
    }

    override fun onBackPressed(): Boolean {
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)

        lifecycleScope.launch {
            val categories: List<Category> = db.piggyBankDAO().getAllCategories()

            verticalRecyclerView.adapter = ButtonAdapter(
                layoutVertical,
                categories,
                ButtonAdapter.OnClickListener(verticalRecyclerView, layoutVerticalColor),
                layoutVerticalColor,
            )
            Log.d("DB", "Resources loaded" + categories.size.toString())
            verticalRecyclerView.adapter?.notifyDataSetChanged()
        }
    }
}