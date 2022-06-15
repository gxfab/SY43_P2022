package com.example.sy43_p2022

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sy43_p2022.database.PiggyBankDatabase
import com.example.sy43_p2022.fragments.HomeFragment
import android.content.Context
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.sy43_p2022.database.entities.Category
import com.example.sy43_p2022.database.entities.SubCategory
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var db: PiggyBankDatabase

    private suspend fun verifyDB(): Boolean {
        db = PiggyBankDatabase.getDatabase(this@MainActivity)
        return db.piggyBankDAO().getAllCategories().isNotEmpty()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            Log.d("APP", "Test if populated")
            if (!verifyDB()) populateDB()
            // populateDB() // force it
        }

        // We inject the HomeFragment into the fragment container
        val transaction = supportFragmentManager.beginTransaction() // mandatory to manipulate fragments
        transaction.replace(R.id.fragment_container, HomeFragment())
        transaction.addToBackStack(null) // no returns
        transaction.commit()
    }

    private fun populateDB() {
        Log.d("APP", "Populating DB")
        val categoriesNames = arrayOf(
            "Food",
            "Services",
            "Car",
            "Lodging",
            "Social Security",
            "Health",
            "Hobbies",
            "Personal",
            "Various",
            "Savings",
            "Events",
            "Holidays",
            "Debts"
        )

        val categoriesSubCategoriesNames = arrayOf(
            arrayOf("Grocery Shopping", "Restaurants", "Bars", "Uber Eats/Deliveroo"),
            arrayOf("Mobile Phone", "Internet", "Streaming"),
            arrayOf("Insurance", "Gasoline", "Reparations", "Parking"),
            arrayOf("Rent", "Insurance", "Electricity", "Local Taxes"),
            arrayOf("Life Insurance", "Disability Insurance", "Retirement", "Pension"),
            arrayOf("Doctor", "Drugstore"),
            arrayOf("Sports", "Movie Theater"),
            arrayOf("Books", "Video Games"),
            arrayOf("Unexpected Expenses"),
            arrayOf("Christmas", "Gifts"),
            arrayOf("Birthday", "Parties"),
            arrayOf("Travel", "Week-ends"),
            arrayOf("Loans")
        )

        lifecycleScope.launch {
            val dao = db.piggyBankDAO()

            dao.nukeCategoryTable()
            dao.nukeSubCategoryTable()

            val categories: MutableList<Category> = mutableListOf<Category>()
            val subCategories: MutableList<SubCategory> = mutableListOf<SubCategory>()

            for ((index, categoryName) in categoriesNames.withIndex()) {
                val category = Category(name = categoryName, catid = index)
                categories += category
                for (subCategoriesNames in categoriesSubCategoriesNames) {
                    for (subCategoryName in subCategoriesNames) {
                        val subCategory = SubCategory(name = subCategoryName, categoryId = category.catid)
                        subCategories += subCategory
                    }
                }
            }

            Log.d("test", subCategories.toString())

            dao.insertCategory(*categories.toTypedArray())
            dao.insertSubCategory(*subCategories.toTypedArray())
        }
    }
}