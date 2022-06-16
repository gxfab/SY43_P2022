package com.example.sy43_p2022.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.IOnBackPressed
import com.example.sy43_p2022.R
import com.example.sy43_p2022.adapter.ButtonAdapter
import com.example.sy43_p2022.database.PiggyBankDatabase
import com.example.sy43_p2022.database.entities.Category
import kotlinx.coroutines.launch

open class CategoryFragment(
    private val mainCategoryName: String,
    private val layoutVertical: Int,
    private val layoutVerticalColor: String,
    private val layout: Int
) : Fragment(), IOnBackPressed {
    private lateinit var db: PiggyBankDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout, container, false)

        db = PiggyBankDatabase.getDatabase(view.context)

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