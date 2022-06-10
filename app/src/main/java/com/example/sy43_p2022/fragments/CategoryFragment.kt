package com.example.sy43_p2022.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.sy43_p2022.R
import com.example.sy43_p2022.adapter.ButtonAdapter
import com.example.sy43_p2022.database.PiggyBankDatabase
import com.example.sy43_p2022.database.entities.Category
import com.example.sy43_p2022.database.entities.SubCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

open class CategoryFragment(
    private val mainCategoryName: String,
    private val layoutVertical: Int,
    private val layoutVerticalColor: String,
    private val layout: Int
) : Fragment() {
    private lateinit var db: PiggyBankDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout, container, false)
        db = PiggyBankDatabase.getDatabase(view.context, CoroutineScope(SupervisorJob()))

        // Go back button (left arrow at the top of the screen)
        view.findViewById<ImageView>(R.id.fragment_return)
            .setOnClickListener {
                val transaction = (activity as FragmentActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, HomeFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            loadSubCategories(view)
        }
    }

    private suspend fun loadSubCategories(view: View) {
        val parentCategory: Category = db.categoryDAO().getCategoryByName(mainCategoryName)
        val subCategories: List<SubCategory> = db.subcategoryDAO().getSubCategoriesFromParentId(parentCategory.id)

        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = ButtonAdapter(
            layoutVertical,
            subCategories,
            ButtonAdapter.OnClickListener(verticalRecyclerView, layoutVerticalColor)
        )
    }
}