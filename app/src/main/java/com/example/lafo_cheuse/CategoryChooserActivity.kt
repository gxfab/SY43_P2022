package com.example.lafo_cheuse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.lafo_cheuse.database.LafoCheuseDatabase
import com.example.lafo_cheuse.database.dao.CategoryDao
import com.example.lafo_cheuse.material.CategoryAdapter
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.viewmodels.CategoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryChooserActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_chooser)

        var mCategories : LiveData<List<Category>>?
        var adapter : CategoryAdapter? = null

        val recyclerView : RecyclerView = findViewById<RecyclerView>(R.id.catgoryList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        adapter = CategoryAdapter()
        recyclerView.adapter = adapter

        val categoryViewModel : CategoryViewModel by viewModels()
        categoryViewModel.getCategories()?.observe(this, Observer<List<Category>> { list ->
            adapter.setCategories(list)
        })










    }
}