package com.sy43_p22.piggy_bank

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sy43_p22.piggy_bank.database.PiggyBankDatabase
import com.sy43_p22.piggy_bank.database.entities.Category
import com.sy43_p22.piggy_bank.database.entities.SubCategory
import com.sy43_p22.piggy_bank.fragments.HomeFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db: PiggyBankDatabase

    interface IOnBackPressed {
        fun onBackPressed(): Boolean
    }

    private suspend fun verifyDB(): Boolean {
        db = PiggyBankDatabase.getDatabase(this@MainActivity)
        return db.piggyBankDAO().getAllCategories().isNotEmpty()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count <= 1) {
            val transaction = supportFragmentManager.beginTransaction() // mandatory to manipulate fragments
            transaction.replace(R.id.fragment_container, HomeFragment())
            transaction.addToBackStack("home")
            transaction.commit()

        } else supportFragmentManager.popBackStack()
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
        transaction.addToBackStack("home")
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

            for (categoryName in categoriesNames) {
                val category = Category(name = categoryName)
                categories += category
            }

            for ((index, subCategoriesNames) in categoriesSubCategoriesNames.withIndex()) {
                for (subCategoryName in subCategoriesNames) {
                    val subCategory = SubCategory(name = subCategoryName, categoryId = index + 1)
                    subCategories += subCategory
                }
            }

            dao.insertCategory(*categories.toTypedArray())
            dao.insertSubCategory(*subCategories.toTypedArray())
        }
    }
}