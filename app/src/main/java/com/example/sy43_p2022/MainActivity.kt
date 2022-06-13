package com.example.sy43_p2022

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sy43_p2022.database.PiggyBankDatabase
import com.example.sy43_p2022.database.entities.Category
import com.example.sy43_p2022.database.entities.SubCategory
import com.example.sy43_p2022.fragments.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db: PiggyBankDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val category1 = Category(catid="food", name="Food")
            val category2 =  Category(catid = "services", name="Services")
            val subcategory1 = SubCategory(subid= "ubereats", name="Uber Eats", categoryId = category1.catid)
            val subcategory2 = SubCategory(subid= "bars", name="bars", categoryId = category1.catid)
            val subcategory3 = SubCategory(subid= "mobilephone", name="Mobile Phone", categoryId = category2.catid)
            lifecycleScope.launch {
                PiggyBankDatabase.getDatabase(this@MainActivity).piggybankDAO().insertCategory(category1)
                PiggyBankDatabase.getDatabase(this@MainActivity).piggybankDAO().insertCategory(category2)
                PiggyBankDatabase.getDatabase(this@MainActivity).piggybankDAO().insertSubCategory(subcategory1, subcategory2, subcategory3)
            }

        // We inject the HomeFragment into the fragment container
        val transaction = supportFragmentManager.beginTransaction() // mandatory to manipulate fragments
        transaction.replace(R.id.fragment_container, HomeFragment())
        transaction.addToBackStack(null) // no returns
        transaction.commit()
    }
}