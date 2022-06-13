package com.example.sy43_p2022

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sy43_p2022.database.PiggyBankDatabase
import com.example.sy43_p2022.fragments.HomeFragment
import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.example.sy43_p2022.database.entities.Category
import com.example.sy43_p2022.database.entities.SubCategory
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var db: PiggyBankDatabase
    private fun doesDatabaseExist(context: Context, dbName: String): Boolean {
        val dbFile: File = context.getDatabasePath(dbName)
        return dbFile.exists()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!doesDatabaseExist(this@MainActivity,"PiggyBank.db" )){
            val category1 = Category(name="Food")
            val category2 =  Category( name="Services")
            val category3 =  Category( name="Car")
            val category4 =  Category( name="Lodging")
            val category5 =  Category( name="Social Security")
            val category6 =  Category( name="Health")
            val category7 =  Category( name="Hobbies")
            val category8 =  Category( name="Personal")
            val category9 =  Category( name="Various")
            val category10 =  Category( name="Savings")
            val category11 =  Category( name="Events")
            val category12 =  Category( name="Holidays")
            val category13 =  Category( name="Debts")
            val subcategory1 = SubCategory(name ="Grocery Shopping", categoryId = 1)
            val subcategory2 = SubCategory(name="Restaurants", categoryId = 1)
            val subcategory3 = SubCategory(name="Bars", categoryId = 1)
            val subcategory4 = SubCategory(name="Uber Eats", categoryId = 1)
            val subcategory5 = SubCategory(name="Mobile Phone", categoryId = 2)
            val subcategory6 = SubCategory(name="Internet", categoryId = 2)
            val subcategory7 = SubCategory(name="Streaming", categoryId = 2)
            val subcategory8 = SubCategory(name="Insurance", categoryId = 3)
            val subcategory9 = SubCategory(name="Gasoline", categoryId = 3)
            val subcategory10 = SubCategory(name="Reparations", categoryId = 3)
            val subcategory11 = SubCategory(name="Parking", categoryId = 3)
            val subcategory12 = SubCategory(name="Rent", categoryId = 4)
            val subcategory13 = SubCategory(name="Insurance", categoryId = 4)
            val subcategory14 = SubCategory(name="Electricity", categoryId = 4)
            val subcategory15 = SubCategory(name="Local Taxes", categoryId = 4)
            val subcategory16 = SubCategory(name="Life Insurance", categoryId = 5)
            val subcategory17 = SubCategory(name="Disability Insurance", categoryId = 5)
            val subcategory18 = SubCategory(name="Retirement", categoryId = 5)
            val subcategory19 = SubCategory(name="Pension", categoryId = 5)
            val subcategory20 = SubCategory(name="Doctor", categoryId = 6)
            val subcategory21 = SubCategory(name="Drugstore", categoryId = 6)
            val subcategory22 = SubCategory(name="Sports", categoryId = 7)
            val subcategory23 = SubCategory(name="Movie Theater", categoryId = 7)
            val subcategory24 = SubCategory(name="Books", categoryId = 8)
            val subcategory25 = SubCategory(name="Video Games", categoryId = 8)
            val subcategory26 = SubCategory(name="Unexpected Expenses", categoryId = 9)
            val subcategory27 = SubCategory(name="Christmas", categoryId = 10)
            val subcategory28 = SubCategory(name="Gifts", categoryId = 10)
            val subcategory29 = SubCategory(name="Birthday", categoryId = 11)
            val subcategory30 = SubCategory(name="Parties", categoryId = 11)
            val subcategory31 = SubCategory(name="Travel", categoryId = 12)
            val subcategory32 = SubCategory(name="Week-ends", categoryId = 12)
            val subcategory33 = SubCategory(name="Loans", categoryId = 13)

            lifecycleScope.launch {
                PiggyBankDatabase.getDatabase(this@MainActivity).piggybankDAO().insertCategory(category1, category2, category3, category4,
                    category5, category6, category7, category8, category9, category10, category11, category12, category13)
                PiggyBankDatabase.getDatabase(this@MainActivity).piggybankDAO().insertSubCategory(subcategory1, subcategory2, subcategory3,
                    subcategory4, subcategory5, subcategory6, subcategory7, subcategory8, subcategory9, subcategory10, subcategory11, subcategory12,
                    subcategory13, subcategory14, subcategory15, subcategory16, subcategory17, subcategory18, subcategory19, subcategory20, subcategory21,
                    subcategory22, subcategory23, subcategory24, subcategory25, subcategory26, subcategory27, subcategory28, subcategory29, subcategory30,
                    subcategory31, subcategory32, subcategory33)
            }
        }

        // We inject the HomeFragment into the fragment container
        val transaction = supportFragmentManager.beginTransaction() // mandatory to manipulate fragments
        transaction.replace(R.id.fragment_container, HomeFragment())
        transaction.addToBackStack(null) // no returns
        transaction.commit()
    }
}