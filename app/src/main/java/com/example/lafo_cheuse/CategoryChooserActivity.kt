package com.example.lafo_cheuse

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lafo_cheuse.material.CategoryAdapter
import com.example.lafo_cheuse.models.Category
import com.example.lafo_cheuse.viewmodels.CategoryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CategoryChooserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_chooser)

        val adapter : CategoryAdapter?

        val recyclerView : RecyclerView = findViewById<RecyclerView>(R.id.catgoryList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        adapter = CategoryAdapter(this)
        recyclerView.adapter = adapter

        val categoryViewModel : CategoryViewModel by viewModels()
        categoryViewModel.getCategories()?.observe(this) { list ->
            adapter.setCategories(list)
        }

        val addCategoryButton : View = findViewById<FloatingActionButton>(R.id.addCategoryButton)
        addCategoryButton.setOnClickListener {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(intent)
        }


    }
}