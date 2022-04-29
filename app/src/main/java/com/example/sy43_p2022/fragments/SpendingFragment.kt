package com.example.sy43_p2022.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.adapter.ButtonAdapter
import com.example.sy43_p2022.database.dao.CategoriesDAO
import com.example.sy43_p2022.database.entity.Category
import kotlin.random.Random

class SpendingFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_spendings, container, false)

        val db = CategoriesDAO()
        val categories: Category = Category("Spending")

        // TODO: update this to use a real database
        for (sub_category_name in db.getSubCategories()) {
            val sub_category = Category(sub_category_name) // TODO: GET SUB CATEGORY FROM THAT SUB-CATEGORY (FOOD: UBER EAT, etc.)
            for (sub_sub_category_name in db.getSubSubCategories(sub_category_name)) {
                val sub_sub_category = Category(sub_sub_category_name)
                sub_sub_category.setObjectiveAmount(Random.nextInt(100))
                sub_category.addSubCategory(sub_sub_category)
            }
            categories.addSubCategory(sub_category)
        }

        //ici, on va récupérer le recyclerview pour afficher la liste des boutons
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = ButtonAdapter(R.layout.item_vertical_gray, categories)

        //Ici, on implémente le bouton du retour
        val clickReturn = view.findViewById<ImageView>(R.id.fragment_return)
        clickReturn.setOnClickListener {
            val transaction = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }
}