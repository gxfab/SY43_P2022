package com.example.sy43_p2022.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sy43_p2022.database.PiggyBankDatabase
import com.example.sy43_p2022.database.entities.Category
import com.example.sy43_p2022.database.entities.SubCategory
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class PopulateDataBase : AppCompatActivity() {

    fun generate(){
    val category1 = Category(catid="food", name="Food")
    val category2 =  Category(catid = "services", name="Services")
    val subcategory1 = SubCategory(subid= "ubereats", name="Uber Eats", categoryId = category1.catid)
    val subcategory2 = SubCategory(subid= "bars", name="bars", categoryId = category1.catid)
    val subcategory3 = SubCategory(subid= "mobilephone", name="Mobile Phone", categoryId = category2.catid)
    lifecycleScope.launch {
        PiggyBankDatabase.getDatabase(this@PopulateDataBase).piggybankDAO().insertCategory(category1)
        PiggyBankDatabase.getDatabase(this@PopulateDataBase).piggybankDAO().insertCategory(category2)
        PiggyBankDatabase.getDatabase(this@PopulateDataBase).piggybankDAO().insertSubCategory(subcategory1, subcategory2, subcategory3)
        }
    }
}